import socket
import threading
import os.path


def get_response(file):
    path = "./content"+file
    check_file = os.path.isfile(path)
    if check_file:
        return "HTTP/1.0 200 OK\n\n"
    else:
        return "HTTP/1.0 404 Not Found\n\n"


def threading_function(connection_socket):
    request = connection_socket.recv(buffer_size).decode()

    first_line = request.partition('\n')[0]
    fll = first_line.split()
    file = fll[1]
    response = get_response(file)
    connection_socket.send(response.encode())
    if response == "HTTP/1.0 200 OK\n\n":
        with open('./content'+file, 'rb') as f:
            chunk = f.read(buffer_size)
            while chunk:
                connection_socket.send(chunk)
                chunk = f.read(buffer_size)
        connection_socket.close()


server_ip = 'localhost'
server_port = 8998
buffer_size = 1024

server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.bind((server_ip, server_port))

server_socket.listen()

print('The server is ready to recieve')

while True:
    connection_socket, addr = server_socket.accept()
    threading.Thread(target=threading_function(connection_socket), args=(connection_socket,)).start()







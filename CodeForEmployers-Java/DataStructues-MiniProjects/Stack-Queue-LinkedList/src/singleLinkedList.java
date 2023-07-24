
public class singleLinkedList<T> {
		private class Node<T>{
			T data; 
			Node<T> next;
			public Node(T data) {
				this.data = data;
				next = null;
			}
		}
		Node<T> head;
		public singleLinkedList() {
			head = null;
		}
		
		public void insert(int index, T data) {
			if(head == null) {
				head = new Node<T>(data);
			}
			else {
				Node<T> clone = head;
				while(clone != null && index > 0) {
					clone = clone.next;
					index--;
				}
				Node<T> added = new Node<T>(data);
				added.next = clone.next;
				clone.next = added;
			}
		}
		
		public void print() {
			int count = 0;
			String str = "";
			Node<T> clone = head;
			while(clone != null) {
				count++;
				clone = clone.next;
			}
			clone = head;
			System.out.print("The singley linked list is: {");
			while(count > 0) {
				str += clone.data;
				if(clone.next != null){
					str += ", ";
				}
				clone = clone.next;
				count--;
			}
			System.out.print(str);
			System.out.println("}");
		}
		
		public static void main(String args[]) {
			singleLinkedList<Integer> test = new singleLinkedList<Integer>();
			test.insert(0, 3);
			test.insert(0, 13);
			test.insert(0, 11);
			test.insert(0, 9);
			test.insert(0, 7);
			test.insert(0, 5);
			test.print();
			//get the data of 9's index
			int temp = test.head.next.next.next.data;
			//replace 9 with 11
			test.head.next.next.next.data = test.head.next.next.next.next.data;
			//set the original 11 to 9
			test.head.next.next.next.next.data = temp;
			test.print();
		}
}

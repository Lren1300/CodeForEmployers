
public class queue<T> {
		int size;
		Node firstNode;
		Node lastNode;
		
		class Node {
			T item;
			Node prev;
			}
		
		public queue(){
			firstNode = null;
			lastNode = null;
			size = 0;
		}
		
		int size() {
			return size;
		}
		
		public boolean isEmpty() {
			if(firstNode == null) {
				return true;
			}
			else {
				return false;
			}
		}
		
		void enqueue(T item) {
			Node prevLast = lastNode;
			lastNode = new Node();
			lastNode.item = item;
			if(isEmpty()) {
				firstNode = lastNode;
			}
			else {
				prevLast.prev = lastNode;
			}
			size++;
		}
		
		T dequeue() {
			if(isEmpty()) {
				throw new NullPointerException("Queue is already empty.");
			}
			else {
				T item = firstNode.item;
				firstNode = firstNode.prev;
				size--;
				return item;
			}
		}
		
		T peek() {
			if(isEmpty()) {
				return null;
			}
			else {
				return firstNode.item;
			}
		}
		
		public static void main(String args[]) {
			queue<Integer> test = new queue<Integer>();
			test.enqueue(9);
			test.enqueue(8);
			test.enqueue(7);
			test.enqueue(6);
			System.out.println("Peek: " + test.peek());
			System.out.println("Size: " + test.size());
			test.dequeue();
			System.out.println("Peek: " + test.peek());
			System.out.println("Size: " + test.size());
			test.enqueue(27);
			System.out.println("Peek: " + test.peek());
			System.out.println("Size: " + test.size());
			test.dequeue();
			test.dequeue();
			test.dequeue();
			System.out.println("Peek: " + test.peek());
			System.out.println("Size: " + test.size());
		}
	}

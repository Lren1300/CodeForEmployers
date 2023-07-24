
public class doubleyLinkedList<T> {
	private class Node<T>{
		T data;
		Node<T> next;
		Node<T> prev;
		public Node(T data) {
			this.data = data;
			next = null;
			prev = null;
		}
	}
	Node<T> head;
	public doubleyLinkedList() {
		head = null;
	}
	
	public void insertBefore(int index, T data) {
		if(head == null) {
			if(index == 0) {
				head = new Node<T>(data);
			}
			else {
				throw new IllegalArgumentException();
			}
		}
		else {
			Node<T> clone = head;
			Node<T> prev = null;
			while(clone != null && index > 0) {
				prev = clone;
				clone = clone.next;
				index--;
			}
				clone.prev = prev;
				Node<T> added = new Node<T>(data);
				if(prev == null) {
					added.next = head;
					head = added;
				}
				else {
					prev.next = added;
					added.next = clone;
				}
			}
		}
	
	public void insertAfter(int index, T data) {
		if(head == null) {
			if(index == 0) {
				head = new Node<T>(data);
			}
			else {
				throw new IllegalArgumentException();
			}
		}
		else {
			Node<T> clone = head;
			Node<T> prev = null;
			while(clone != null && index > 0) {
				prev = clone;
				clone = clone.next;
				index--;
			}
				clone.prev = prev;
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
		System.out.print("The doubley linked list is: {");
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
		doubleyLinkedList<Integer> test = new doubleyLinkedList<Integer>();
		test.insertAfter(0, 3);
		test.insertAfter(0, 13);
		//use before method to show doubley links
		test.insertBefore(1, 11);
		test.insertAfter(0, 9);
		test.insertBefore(1, 7);
		test.insertAfter(0, 5);
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

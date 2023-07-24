import java.util.ArrayList;

public class stack<I> {
	ArrayList<I> stack;
	int top = -1;
	int size;
	
	stack(int size){
		this.size = size;
		this.stack = new ArrayList<I>(size);
	}
	
	//Methods have added print statements for documentation
	
	void push(I value){
		
		if(top + 1 == size) {
			System.out.println("Error, overflow");
		}
		else {
			top++;
			if (stack.size() > top) {
				stack.set(top, value);
			}
			else {
				stack.add(value);
				System.out.println("Pushed: " + value);
			}
		}
	}
	
	I pop() {
		if (top == -1) {
			System.out.println("Stack is already empty");
			return null;
		}
		else {
			I temp = stack.get(top);
			stack.remove(top);
			top--;
			System.out.println("Popped: " + temp);
			return temp;
		}
	}
	
	//Methods to test stack
	I top() {
		if(top == -1) {
			System.out.println("Stack is empty");
			return null;
		}
		else {
			return stack.get(top);
		}
	}
	
	void emptyStack() {
		if(top == -1) {
			System.out.println("Stack is empty");
		}
		else {
			while(top > -1) {
				I temp = stack.get(top);
				stack.remove(top);
				System.out.println("Popped: " + temp);
				top--;
			}
		}
	}
	public static void main(String args[]) {
		stack<Integer> test = new stack<Integer>(10);
		test.push(6);
		test.push(7);
		test.push(8);
		test.push(9);
		test.pop();
		test.push(27);
		System.out.println("Top: " + test.top());
		test.emptyStack();
		System.out.println("Top: " + test.top());
		test.push(9);
		System.out.println("Top: " + test.top());
		
	}
}

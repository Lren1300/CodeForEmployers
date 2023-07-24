
import java.util.*;

public class heapArray {
	int heapSize;
	ArrayList<Integer> array;
	
	public heapArray() {
		array = new ArrayList<Integer>();
		heapSize = array.size();
	}
	
	public int parent(int i) {
		return (i)/2;
	}
	
	public int left(int i) {
		return (2*i);
	}
	
	public int right(int i) {
		return (2*i)+1;
	}
	
//	public void maxHeapify(ArrayList<Integer> A, int i) {
//		int L = left(i);
//		int R = right(i);
//		int largest;
//		if(L <= A.size() && A.get(L) > A.get(i)) {
//			largest = L;
//		}
//		else {
//			largest = R;
//		}
//		if(R <= A.size() &&  A.get(R) > A.get(largest)) {
//			largest = R;
//		}
//		if(largest != i) {
//			int temp;
//			temp = A.get(i);
//			A.set(i, A.get(largest));
//			A.set(largest, temp);
//			maxHeapify(A, largest);
//		}
//	}
//	
//	public void buildMaxHeap(ArrayList<Integer> A) {
//		heapSize = A.size();
//		for(int i=A.size() / 2; i<=1; i--) {
//			maxHeapify(A, i);
//		}
//	}
//	
//	public int heapMax(ArrayList<Integer> A) {
//		return A.get(0);
//	}
//	
//	public int heapExtractMax(ArrayList<Integer> A) {
//		if(heapSize < 1) {
//			System.out.println("heap underflow");
//			return 0;
//		}
//		int max = A.get(0);
//		A.set(0, A.get(heapSize));
//		heapSize--;
//		maxHeapify(A, 1);
//		return max;
//	}
//	
//	public void heapIncreaseKey(ArrayList<Integer> A, int i, int key) {
//		if(key < A.get(i)) {
//			System.out.println("New key is smaller than current key");
//			return;
//		}
//		A.set(i, key);
//		while(i>1 && A.get(parent(i)) < A.get(i)) {
//			int temp;
//			temp = A.get(i);
//			A.set(i, A.get(parent(i)));
//			A.set(parent(i), temp);
//		}
//	}
//	public void maxHeapInsert(ArrayList<Integer> A, int key) {
//		heapSize = heapSize + 1;
//		A.set(heapSize, -999999999);
//		heapIncreaseKey(A, heapSize, key);
//	}
	
	//Min for assignment
	public void minHeapify(ArrayList<Integer> A, int i) {
		int L = left(i);
		int R = right(i);
		int smallest;
		if(L <= heapSize && A.get(L) < A.get(i)) {
			smallest = L;
		}
		else {
			smallest = i;
		}
		if(R <= A.size() && A.get(R) < A.get(smallest)) {
			smallest = R;
		}
		if(smallest != i) {
			int temp;
			temp = A.get(i);
			A.set(i, A.get(smallest));
			A.set(smallest, temp);
			minHeapify(A, smallest);
		}
	}
	
	public void buildMinHeap(ArrayList<Integer> A) {
		heapSize = A.size();
		for(int i=(A.size()/2)-1; i>=1; i--) {
			minHeapify(A, i);
		}
	}
	
	public int heapMin(ArrayList<Integer> A) {
		return A.get(0);
	}
	
	public int heapExtractMin(ArrayList<Integer> A) {
		if(heapSize < 1) {
			System.out.println("heap underflow");
			return 0;
		}
		int min = A.get(0);
		A.set(0, A.get(heapSize));
		heapSize--;
		minHeapify(A, 0);
		return min;
	}
	
	public void heapDecreaseKey(ArrayList<Integer> A, int i, int key) {
		if(key > A.get(i)) {
			System.out.println("New key is bigger than current key");
			return;
		}
		A.set(i, key);
		while(i>1 && A.get(parent(i)) > A.get(i)) {
			int temp;
			temp = A.get(i);
			A.set(i, A.get(parent(i)));
			A.set(parent(i), temp);
		}
	}
	
	public void minHeapInsert(ArrayList<Integer> A, int key) {
		heapSize = heapSize + 1;
		A.set(heapSize, -999999999);
		heapDecreaseKey(A, heapSize, key);
	}
	
	public void arrayFillAsc(ArrayList<Integer> A, int size) {
		for(int i=0; i<size; i++) {
			A.add(i+1);
		}
		heapSize = A.size();
	}
	
	public void arrayFillDec(ArrayList<Integer> A, int size) {
		for(int i=size; i>0; i--) {
			A.add(i+1);
		}
		for(int i=size; i>0; i--) {
			A.set(size-i, i);
		}
		heapSize = A.size();
	}
	
	public void arrayFillRan(ArrayList<Integer> A, int size) {
		for(int i=0; i<size; i++) {
			int random = (int)(Math.random()*size);
			A.add(random);
		}
		heapSize = A.size();
	}
	
	public void emptyArray(ArrayList<Integer> A) {
		for(int i=0; i < A.size(); i++) {
			A.set(i, null);
		}
	}
	
	public static void main(String args[]) {
		long startTime = 0;
		long stopTime = 0;
		heapArray test = new heapArray();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		test.arrayFillRan(test.array, 10);
		System.out.println("The array before the heapify: " + test.array.toString());
		test.buildMinHeap(test.array);
		System.out.println("The array after the heapify: " + test.array.toString());
		
		test.emptyArray(test.array);
		
		System.out.println("");
		
		heapArray test2 = new heapArray();
		//Already full ascending heap
		test2.arrayFillAsc(test2.array, 1000);
		startTime = System.nanoTime();
		test2.buildMinHeap(test2.array);
		stopTime = System.nanoTime();
		System.out.println("The time for a full array in ascending order is: " + (stopTime-startTime));
		test2.emptyArray(test2.array);
		
		heapArray test3 = new heapArray();
		//Already full descending heap
		test3.arrayFillDec(test3.array, 1000);
		startTime = System.nanoTime();
		test3.buildMinHeap(test3.array);
		stopTime = System.nanoTime();
		System.out.println("The time for a full array in descending order is: " + (stopTime-startTime));
		test3.emptyArray(test3.array);
		
		heapArray test4 = new heapArray();
		//Already full random heap
		test4.arrayFillRan(test4.array, 1000);
		startTime = System.nanoTime();
		test4.buildMinHeap(test4.array);
		stopTime = System.nanoTime();
		System.out.println("The time for a full array in random order is: " + (stopTime-startTime));
		test4.emptyArray(test4.array);
		
		System.out.println("");
		
		heapArray test5 = new heapArray();
		//Entering one by one ascending order
		test5.arrayFillAsc(temp, 1000);
		startTime = System.nanoTime();
		for(int i=0; i<temp.size(); i++) {
			int newIndex = temp.get(i);
			test5.array.add(newIndex);
			test5.buildMinHeap(test5.array);
		}
		stopTime = System.nanoTime();
		System.out.println("The time for a one by one array in ascending order is: " + (stopTime-startTime));
		test.emptyArray(test5.array);
		test.emptyArray(temp);
		
		ArrayList<Integer> temp2 = new ArrayList<Integer>();
		heapArray test6 = new heapArray();
		//Entering one by one descending order
		test6.arrayFillDec(temp2, 1000);
		startTime = System.nanoTime();
		for(int i=0; i<temp2.size(); i++) {
			int newIndex = temp2.get(i);
			test6.array.add(newIndex);
			test6.buildMinHeap(test6.array);
		}
		stopTime = System.nanoTime();
		System.out.println("The time for a one by one array in decscending order is: " + (stopTime-startTime));
		test.emptyArray(test6.array);
		test.emptyArray(temp2);
		
		ArrayList<Integer> temp3 = new ArrayList<Integer>();
		heapArray test7 = new heapArray();
		//Entering one by one ascending order
		test7.arrayFillRan(temp3, 1000);
		startTime = System.nanoTime();
		for(int i=0; i<temp3.size(); i++) {
			int newIndex = temp3.get(i);
			test7.array.add(newIndex);
			test7.buildMinHeap(test7.array);
		}
		stopTime = System.nanoTime();
		System.out.println("The time for a one by one array in random order is: " + (stopTime-startTime));
		test.emptyArray(test7.array);
		test.emptyArray(temp3);
	}
	
}

import java.util.Scanner;
import java.util.ArrayList;

public class insertionSort {
	
	public ArrayList<Integer> sort(ArrayList<Integer> sortedArray) {
		int currentIndexCopy, previousIndex;
		for(int currentIndex=1; currentIndex<sortedArray.size(); currentIndex++) {
			currentIndexCopy = sortedArray.get(currentIndex);
			previousIndex = currentIndex-1;
			while((previousIndex>-1) && (sortedArray.get(previousIndex)>currentIndexCopy)) {
				sortedArray.set((previousIndex+1), sortedArray.get(previousIndex));
				previousIndex--;
			}
			sortedArray.set((previousIndex+1), currentIndexCopy);
		}
		return sortedArray;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		insertionSort test = new insertionSort();
		ArrayList<Integer> output = new ArrayList<Integer>();
		int currKey;
		
		for(int enteredNumber=0; enteredNumber<5; enteredNumber++) {
			System.out.print("Enter a number: ");
			currKey = input.nextInt();
			output.add(currKey);
			System.out.println("The array before insertion sort: " + output.toString());
			output = test.sort(output);
			System.out.println("The array after insertion sort: " + output.toString());
			System.out.println("");
		}
		input.close();
	}
}

import java.util.Random;

public class hashCollisions {
	
	int[] hashTable = new int[TABLE_SIZE];
	static int TABLE_SIZE = 10001;
	int currentRandom;
	int linear = 0;
	int quad = 0;
	int sec = 0;
	int col = 0;
	Random rand = new Random();
	
	public void fillArray(int[] array) {
		for(int ht=0; ht<TABLE_SIZE; ht++){
			array[ht] = -1;
		}
	}
	
	public int linearProb() {
		col = 0;
		for(int i=0; i<500; i++) {
			currentRandom = rand.nextInt(100000);
			if(hashTable[(currentRandom % 300) % TABLE_SIZE] != -1) {
				linear = 1;
				col++;
				while(hashTable[((currentRandom % 300) + linear) % TABLE_SIZE] != -1) {
					linear++;
					col++;
				}
				hashTable[((currentRandom % 300) + linear) % TABLE_SIZE] = currentRandom;
			}
			else {
				hashTable[(currentRandom % 300) % TABLE_SIZE] = currentRandom;
			}
		}
		return col;
	}
	
	public int quadraticProb() {
		col = 0;
		for(int i=0; i<500; i++) {
			currentRandom = rand.nextInt(100000);
			if(hashTable[(currentRandom % 300) % TABLE_SIZE] != -1) {
				quad = 1;
				while(hashTable[((currentRandom % 300) + (quad*quad)) % TABLE_SIZE ] != -1) {
					quad++;
					col++;
				}
				hashTable[((currentRandom % 300)+ (quad*quad)) % TABLE_SIZE] = currentRandom;
			}
			else {
				hashTable[(currentRandom % 300) % TABLE_SIZE] = currentRandom;
			}
		}
		return col;
	}
	
	public int doubleHashSecondFunc(int currentRan) {
		int temp = 9973-(currentRan % 9973);
		return temp;
	}
	
	public int doubleHashFunc() {
		col = 0;
		for(int i=0; i<500; i++) {
			currentRandom = rand.nextInt(100000);
			if(hashTable[(currentRandom % 300) % TABLE_SIZE] != -1) {
				sec = 1;
				while(hashTable[sec*((currentRandom % 300) + doubleHashSecondFunc(currentRandom)) % TABLE_SIZE ] != -1) {
					sec++;
					col++;
				}
				hashTable[sec*((currentRandom % 300) + doubleHashSecondFunc(currentRandom))  % TABLE_SIZE] = currentRandom;
			}
			else {
				hashTable[(currentRandom % 300) % TABLE_SIZE] = currentRandom;
			}
		}
		return col;
	}
	
	public static void main(String args[]) {
		hashCollisions test = new hashCollisions();
		test.fillArray(test.hashTable);
		System.out.println("Linear Probing collisions: " + test.linearProb());
		System.out.println("Quadratic Probing collisions: " + test.quadraticProb());
		System.out.println("Double Hash collisions: " + test.doubleHashFunc());
	}
}


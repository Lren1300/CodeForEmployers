import java.lang.Math;

public class growthRate {

public void test() {
	double someVariable = 0;
	int n = 0;
	for(int iteration = 0; iteration < 3; iteration++) {
		if(iteration == 0) {
			n = 1000;
		}
		else if(iteration == 1) {
			n = 10000;
		}
		else if(iteration == 2) {
			n = 100000;
		}
		
		long startTime1 = System.nanoTime();
		//(a)
		for(int i1 = 0; i1 < n; i1++){
			someVariable = Math.log10(someVariable) + 3;
		}
		long endTime1 = System.nanoTime();
		
		
		long startTime2 = System.nanoTime();
		//(b)
		for(int i2 = 0; i2 < n; i2++){
			for(int j2 = 0; j2 < n; j2++){
				someVariable = Math.log10(someVariable) + 3;
			}
		}
		long endTime2 = System.nanoTime();
		
		long startTime3 = System.nanoTime();
		//(c)
		for(int i3 = 0; i3 < n; i3++){
			for(int j3=0; j3 < i3; j3++){
				someVariable = Math.log10(someVariable) + 3;
			}
		}
		long endTime3 = System.nanoTime();
		
		long startTime4 = System.nanoTime();
		//(d)
		for(int i4 = 0; i4 < n; i4++){
			for(int j4 = 0; j4 < i4; j4++){
				if(j4 % 2 == 0){
					someVariable = Math.log10(someVariable) + 3;
				}
			}
		}
		long endTime4 = System.nanoTime();
		
		System.out.println("a." + iteration + ": " +((endTime1-startTime1)/1000000));
		System.out.println("b." + iteration + ": " +((endTime2-startTime2)/1000000));
		System.out.println("c." + iteration + ": " +((endTime3-startTime3)/1000000));
		System.out.println("d." + iteration + ": " +((endTime4-startTime4)/1000000));
		System.out.println("");
	}

}

public static void main(String args[]) {
	growthRate gr = new growthRate();
	gr.test();
}
}


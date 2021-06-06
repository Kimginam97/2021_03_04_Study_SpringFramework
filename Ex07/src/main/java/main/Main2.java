package main;

import sday04.ImpeCalculator;
import sday04.RecCalculator;

public class Main2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImpeCalculator impeCalculator = new ImpeCalculator();
		long start = System.nanoTime();
		impeCalculator.factorial(5);
		long end = System.nanoTime();
		System.out.printf("ImpeCalculator: factorial(%d) 실행시간 : %d\n",5,(end-start));
		
		RecCalculator recCalculator = new RecCalculator();
		start = System.nanoTime();
		recCalculator.factorial(5);
		end = System.nanoTime();
		System.out.printf("RecCalculator: factorial(%d) 실행시간 : %d\n",5,(end-start));
	}

}

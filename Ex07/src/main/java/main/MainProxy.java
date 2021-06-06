package main;

import sday04.ExeTimeCalculator;
import sday04.ImpeCalculator;

public class MainProxy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ExeTimeCalculator ttCal1 = new ExeTimeCalculator(new ImpeCalculator());
		System.out.println(ttCal1.factorial(20));
		
		ExeTimeCalculator ttCal2 = new ExeTimeCalculator(new ImpeCalculator());
		System.out.println(ttCal2.factorial(20));
		
	}

}

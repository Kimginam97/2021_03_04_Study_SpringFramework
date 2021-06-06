package sday04;

public class RecCalculator implements Calculator{
	
	@Override
	public long factorial(long num) {
		// TODO Auto-generated method stub
		//long start = System.currentTimeMillis();
		
		if(num == 0)
			return 1;
		else {
			//long end =System.currentTimeMillis();
			//System.out.printf("RecCalculator: factorial(%d) 실행시간 : %d\n",num,(end-start));
			return num*factorial(num-1);
		}
		

	}

}

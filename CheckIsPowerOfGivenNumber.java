import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class CheckIsPowerOfGivenNumber {
	/*
	* This is a O(1) operation, a special case when we want to check if given number "x" is power of 2 or not
	* The logic is based on the fact that all numbers with power of 2 have format "1000...." ( the 0 extending n-1) times. Thus, the (x-1) gives a number with MSB as 0, and all other values 111... ( extending n-1 times ).
	* Thus, when x & (x-1) gives a FALSE result,we know that x and (x-1) have no bits in common , making x a power of 2 definitely
	*/
	
	public static boolean checkIsPowerOfTwo(int x) {
		return ( (x & (x-1)) == 0 );
	}	
	/*
	*This is a straightforward check to see if the format of a number "x" is of "10000..." or not
	*if number of 1's in the input x is greater than 1, then we can stop the check and say it is not a power of 2.
	*This is O(n) time algorithm, as each bit needs to be checked in this case.
	*/
	public static boolean checkIsPowerOfTwo_RightShift(int x) {
		int numberOfRightShifts = 0;
		while ( (x > 0) && (numberOfRightShifts <= 1)) {
			if (( x & 1 ) == 1)
				numberOfRightShifts++;
			x = x >> 1; //right shift by 1 bit	
		}	
		return (numberOfRightShifts == 1);
				
	}	
	public static void main(String[] args) throws IOException {
		int n1;
		System.out.println("Enter the number to check :");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			n1 = Integer.parseInt(br.readLine());
			if ( n1 <= 0) { 
				System.out.println("Power of 2 is only valid for positive values greater than 0");
				return;
			}	
			System.out.println("Given number " + n1 + " is power of 2(yes/no):" + CheckIsPowerOfGivenNumber.checkIsPowerOfTwo_RightShift(n1));
			System.out.println("Given number " + n1 + " is power of 2(yes/no):" + CheckIsPowerOfGivenNumber.checkIsPowerOfTwo(n1));			
		}
		finally {
			if (br!= null) 
				br.close();
		}	
	}
}
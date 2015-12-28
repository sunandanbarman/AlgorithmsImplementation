/**
* Sieve of Eratosthenes method to find prime numbers in the range from 2..N ( given N)
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.math.*;

class SieveOfEratosthenes {

	/**
	 * @param args
	 */
	public static int getNumber(BufferedReader br) throws IOException{
		int nResult = 0;
		String inputNumber;
		String regex = "^[0-9]+$"; 
		while ( true ) { 
			inputNumber	= br.readLine();	
			if ( inputNumber.matches(regex) ) {
				nResult = Integer.parseInt(inputNumber);
				if (nResult > 0) 
					break;
				System.out.println("\nPlease enter a valid number ! Try again...");	
			}	
			else {
				System.out.println("\nPlease enter a valid number ! Try again...");
			}
				
		}
		return nResult;
	}	 

	public static void findPrimes(int N) {
		int upperBound = (int)Math.sqrt((double)N);
		boolean[] isComposite = new boolean[N+1];
		for(int m=2; m <= upperBound; m++) {
			if (!isComposite[m]) {
				for(int k=m*m; k<= N; k= k+m) {
					isComposite[k] = true;
				}
			}
		}
		for(int k=2; k<= N; k++) {
			if (!isComposite[k])
				System.out.print(k + " : ");
		}
	}
	public static void main(String[] args) throws IOException{
		System.out.println("Enter the maximum range required :");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = SieveOfEratosthenes.getNumber(br);
		SieveOfEratosthenes.findPrimes(N);
	}

}
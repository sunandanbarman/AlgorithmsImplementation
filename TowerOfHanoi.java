/*
* Tower of Hanoi implementation
* Note : We limit number of disks to 5 , since number of steps required is 
* 2^n-1 and we don't want to run this algorithm forever
* The pegs are named as "A", "B" and "C"
* Basic explanation : Imagine that there are 3 rings that we want to move from peg A to peg B.
* If the largest ring is to be moved from peg A to peg B, then all other rings must be on peg C.
* This is the first call of the recursion
* Next, we want to move back all disks from peg C to peg B. Again, largest ring in peg C must be the
* 2nd last in peg B. Hence we move all the rings from C to A.
* Follow this recursively, we get the solution
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class SolveHanoi {
	private int numOfDisks;
	SolveHanoi(int numOfDisks) {
		this.numOfDisks = numOfDisks;
	}
	private void moveDisk(String fromPeg,String toPeg) {
		System.out.println("Moving " + fromPeg + " to " + toPeg);
	}
	public void moveTower(int numOfDisks,String fromPeg,String toPeg,String interPeg) {
		if (numOfDisks >= 1) {
			moveTower(numOfDisks-1,fromPeg,interPeg,toPeg);
			moveDisk(fromPeg,toPeg);
			moveTower(numOfDisks-1,interPeg,toPeg,fromPeg);
		}
	}
}
class TowerOfHanoi {
	
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
	
	public static void main(String []args) throws IOException{
		System.out.println("Enter the number of disks (max 5):");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int nDisks = TowerOfHanoi.getNumber(br);
		if (nDisks >= 5) {
			System.out.println("Exceeded the MAX constraint. Algorithm will run with value 5...");
		}
		SolveHanoi solve = new SolveHanoi(nDisks);
		solve.moveTower(nDisks,"A","B","C");	
		
	}
}
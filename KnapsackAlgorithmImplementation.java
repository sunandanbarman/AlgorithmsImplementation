import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.io.IOException;

/**
*
*/
class KnapsackItemsFinder {
	private int knapsackWeight, numberOfItems;
	private int Profit[][];
	private int Dir[][];
	/**
	*@param : val1, val2 --> elements to find the maximum out of 
	*/
	public int Find_Max(int val1, int val2) {
		if( val1 > val2 ) 
			return val1;
		else 
			return val2;
	}	
	/**
	* The algorithm works as follows :
	* a) If w[i] > j , then Profit[i][j] = Profit[i-1][j] ( as we cannot put item i 
	* 	 in the knapsack , and thus the optimal sequence is from item 1...i-1
	* b) If w[i] <= j, then we have 2 choices , either 
	*    -- we put item I in the knapsack, which changes Profit[i][j]
	*    -- we dont put item I in the knapsack, in this case, Profit[i][j] will remain
	*       the same as Profit[i-1][j]
	* Since our goal is to find maximum profit, we will do a max() on these 2 scenarios
	* to get best possible output
	*/
	public void	getData() throws IOException{
		int i,j, val;
		int profitOfItems[], weight[];
		
		System.out.println("Enter the weight of the Knapsack ( must be positive):");
		knapsackWeight = KnapsackAlgorithmImplementation.getNumber();
		
		System.out.println("\nEnter the number of items to put in knapsack ( must be positive):");
		numberOfItems = KnapsackAlgorithmImplementation.getNumber();
		
		profitOfItems = new int[numberOfItems + 1];
		for ( i = 1 ; i <= numberOfItems ; i++) {
			System.out.println("Enter profit of item " + String.valueOf(i));
			profitOfItems[i] = KnapsackAlgorithmImplementation.getNumber();
		}	
		
		weight = new int[numberOfItems + 1];
		for ( i = 1 ; i <= numberOfItems ; i++) {
			System.out.println("Enter weight of item " + String.valueOf(i));
			weight[i] = KnapsackAlgorithmImplementation.getNumber();
		}	
		
		Profit = new int[numberOfItems + 1][knapsackWeight + 1];
		Dir	   = new int[numberOfItems + 1][knapsackWeight + 1];
		
		for ( j = 0 ; j <= knapsackWeight; j++) {
			Profit[0][j] = 0;	
		}	
		for ( i = 0 ; i <= numberOfItems; i++) {
			Profit[i][0] = 0;	
		}
		for ( i = 1 ; i <= numberOfItems; i++) { 
			for ( j = 1 ; j <= knapsackWeight; j++) {
				if ( weight[i] > j) {
					Profit[i][j] = Profit[i-1][j];
					Dir[i][j]    = 1;
				}
				else {
					val = profitOfItems[i] + Profit[i-1][j - weight[i]];
					Profit[i][j] = Find_Max( Profit[i-1][j] , val);  
					if ( Profit[i][j] == val)
						Dir[i][j] = 2;
					else
						Dir[i][j] = 1;	
				}	
			}
		}
		System.out.println("Maximum profit that can fit in the knapsack is :" + Profit[numberOfItems][knapsackWeight]);
		Print_KnapsackItems(Dir,weight);		
	}
	
	/**
	* @param: Dir[][] , we will print out the item i, if Dir[i][j] = "2",
	* and we move to the item Dir[i-1][j-w[i]]
	* If Dir[i][j] = "1", item is not included in knapsack, we need not do anything
	*/
	public void Print_KnapsackItems(int Dir[][], int weight[]) { 
		int i = numberOfItems;
		int j = knapsackWeight;
		System.out.println("Items in Knapsack are :");
		for ( ; i >= 1; i--) {
			if ( Dir[i][j] == 2 ) {
				System.out.print(i + " ");
				j = j - weight[i];
			}	
		}	
	}	
	
}	


class KnapsackAlgorithmImplementation {
	private static BufferedReader br;
	/**
	 * @param args
	 */
	public static int getNumber() throws IOException{
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
	public static void main(String[] args){
		int  i,knapsackWeight =0, numberOfItems = 0;
		int profitOfItems[], weight[];
		br = new BufferedReader(new InputStreamReader(System.in));
		try {
			KnapsackItemsFinder knapsackItems = new KnapsackItemsFinder();
			knapsackItems.getData();			
		}
		catch(IOException ex) {
		}
		finally {
			try {
				br.close();
			}
			catch(Exception ex2) {
			}	
		}
		
	}

}
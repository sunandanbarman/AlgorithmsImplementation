import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Formatter;
import java.util.logging.SimpleFormatter;
 
/*
Algorithm adapted from CLRS book section 15.2 
*/
public class MatrixMultiplicationOptimal {
	
	private static final Logger logger = Logger.getLogger("MatrixMultiplicationOptimal");
	private static String optimalSequence = "";
	public static void printOptimalParentheses(int s[][], int i,int j) {
		if ( i == j)
		{
			System.out.print(" A" + i);
				
		}
		else  {
			System.out.print("(");
			printOptimalParentheses(s,i,s[i][j]);
			printOptimalParentheses(s,s[i][j]+1 ,j);
			System.out.print(")");
		}		
	}	
	public static void FindMinimumCalculations(ArrayList<Integer> dimensions) {
		int i ,j, k , nLength, orgJ , newMinimum, nCounter = 0;
		boolean bBreakOutOfLoop = false;	
		nLength = dimensions.size();
		int m[][] = new int[nLength + 1][nLength + 1] ;
		int s[][] = new int[nLength+1][nLength+1];
		for (  i = 1 ; i < nLength ; i ++ ) { 
			for (  j = 1 ;j < nLength ; j++ ) { 
				if ( i == j) 
					m[i][j] = 0;
				else 
					m[i][j] = -1; //treat as "undef"
			}	
		}
		i  =1;
		j  =1;	
		while (  i < nLength)  {
			orgJ = j;
			while ( j < nLength)  {
				if ( i != j ) { // main diagonal elements are skipped
					newMinimum = -1;
					for ( k = i ; k <  j ; k++) {
						newMinimum = m[i][k] + m[k+1][j] + dimensions.get(i-1) * dimensions.get(k) * dimensions.get(j);
						if (( m[i][j] > newMinimum) || (m[i][j] == -1)) 
						{
							m[i][j] = newMinimum;
							s[i][j] = k; 
						}	
					}	
					if (( i == 1 ) && ( j == nLength -1)) {
						bBreakOutOfLoop = true;
						break;	
					}
					i++;
					j++;
					nCounter++;	
				}
				else {
					j++;	
				}
				
			}
			i =1;
			j =orgJ + 1;
			if ( bBreakOutOfLoop ) 
				break;
		}
		//minimum number of computations required is m[nLength-1][nLength-1]	
		logger.info("The minimum number of computations required is " + m[1][nLength-1]);
		printOptimalParentheses(s,1,nLength-1);		
			
	}	
	
	public static void setFormatterOptions() throws IOException{
		SimpleFormatter simpleFormatter  = new SimpleFormatter();
		FileHandler fileHandler = new FileHandler("./info.log");
		logger.addHandler(fileHandler);
		logger.setLevel(Level.ALL);
		fileHandler.setLevel(Level.ALL);
		fileHandler.setFormatter(simpleFormatter);	
	}	
	public static void main(String []args) throws IOException {
		ArrayList<Integer> dimensions ;
		String inputNumber;
		MatrixMultiplicationOptimal.setFormatterOptions();
		
		System.out.println("Enter the dimensions of the matrix ( terminate with a ; )");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			dimensions 		  = new ArrayList<>();
			while ( true ) { 
				inputNumber		  = br.readLine();
				if ( inputNumber.equals(";")) {
					break;
				}	
				if ( inputNumber.matches("[0-9]+") ) {
					dimensions.add(Integer.parseInt(inputNumber));	
						
				}	
				else {
					logger.warning("\nPlease enter a valid number ! Try again...");
				}
					
			}
			MatrixMultiplicationOptimal.FindMinimumCalculations(dimensions);
		}
		catch(Exception ex) {
			logger.warning("Exception occurred in input process..." + ex.getMessage());
		}	
		finally {
			try {
				br.close();
			}
			catch(Exception ex2) {
				logger.warning("Exception occurred in bufferedReader close process..." );
			}	
		}		
	}

}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Formatter;
import java.util.logging.SimpleFormatter;
 
 
/*
Algorithm follows simple divide-and-conquer strategy to achieve O(logn) complexity
For understanding DaC strategy, consult CLRS book
Using dynamic programming, computations can be reduced : This will be investigated in another program
*/

public class FindPowerOfRealNumber {
	
	private static final Logger logger = Logger.getLogger("MatrixMultiplicationOptimal");
	private static double result = 0.0;
	public static double FindPow(double baseNumber, int exponent) {
		int midRangeOfExponent;
		midRangeOfExponent = exponent / 2;
		if ( exponent == 2 ) {
			return baseNumber * baseNumber;
			
		}	
		else if ( exponent == 1 ) {
			return baseNumber;
		}
		else {
			if (exponent % 2 == 0) { //even
				result = FindPow(baseNumber,midRangeOfExponent) * FindPow(baseNumber,midRangeOfExponent);
			}
			else { //odd
				result = FindPow(baseNumber,midRangeOfExponent) ;
				result = result * FindPow(baseNumber,midRangeOfExponent+ 1);
			}		
		}
		return result;
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
		String inputNumber;
		MatrixMultiplicationOptimal.setFormatterOptions();
		double baseNumber;
		int exponent;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Enter the base number :");
			while ( true ) { 
				inputNumber		  = br.readLine();	
				if ( inputNumber.matches("^[0-9]*\\.?[0-9]+$") ) {
					baseNumber = Double.parseDouble(inputNumber);
					if (baseNumber > 0.0)
					{		
						break;	
					}
					else 
						logger.warning("\nPlease enter a valid number ! Try again...");	
				}	
				else {
					logger.warning("\nPlease enter a valid number ! Try again...");
				}
					
			}
			System.out.println("Enter the exponent :");
			while ( true ) { 
				inputNumber		  = br.readLine();	
				if ( inputNumber.matches("[0-9]+") ) {
					exponent = Integer.parseInt(inputNumber);
					if (exponent > 0.0)
					{		
						break;	
					}
					else 
						logger.warning("\nPlease enter a valid number ! Try again...");	
				}	
				else {
					logger.warning("\nPlease enter a valid number ! Try again...");
				}
					
			}
			
			logger.info("\nResult of x^y is " + FindPowerOfRealNumber.FindPow(baseNumber,exponent));
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
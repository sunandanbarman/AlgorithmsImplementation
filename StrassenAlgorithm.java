import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.io.IOException;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/*
* Strassens Algorithm as adapted from CLRS book Section 4.2
*/ 
class StrassenMultiplier {
	private int matrix1[][],matrix2[][] , matrixResult[][];
	private int dimension, midDimension;
	private BufferedReader br;
	private StringBuilder textToLog;
	private boolean bLogFile;
	/*
	*
	*/	
	public StrassenMultiplier(BufferedReader br) {
		this.br = br;
		this.textToLog  = new StringBuilder();
	}
	/*
	*
	*/	
	public void setTextToLog(StringBuilder textToLog) {
		this.textToLog = textToLog;
	}
	/*
	*
	*/	
	public StringBuilder getTextToLog() {
		return this.textToLog;
	}		
	/*
	*
	*/
	public void setLogOptions(boolean bLogFile) {
		this.bLogFile = bLogFile;
	}	
	/*
	*
	*/		
	public boolean getLogOptions() {
		return this.bLogFile;
	}
	/*
	*
	*/	
	public void setDimensions(int dimension) {
		this.dimension  = dimension;
		matrix1 		= new int[dimension][dimension];
		matrix2 		= new int[dimension][dimension];
		matrixResult 	= new int[dimension][dimension];
		midDimension    = dimension / 2;
	}
	/**
	 * 
	 * @param filePath : file to be be made empty
	 */
	public void EmptyFileContents(String filePath) {
		try {
			FileWriter fw = new FileWriter(filePath);
			PrintWriter pw = new PrintWriter(fw);
			pw.write("");
			pw.flush(); 
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}	
	/**
	 * @param textToLog
	 * function to generate output log file of all the results
	 */
	public void LogIntoFile(String textToLog) {
		BufferedWriter bWriter = null;
		File outputFile = new File("info.log");
		 try {
			if (!outputFile.exists()) {
				outputFile.createNewFile();
			}
			EmptyFileContents("info.log");
			bWriter = new BufferedWriter(new FileWriter(outputFile, true));
		    bWriter.write(textToLog);
		    bWriter.newLine();
		    bWriter.flush();
	      } catch (IOException ioe) {
	    	  ioe.printStackTrace();
	      } finally {                       // always close the file
	    	  if (bWriter != null) try {
	    		  bWriter.close();
	    	  } catch (IOException ioe2) {
		        // just ignore it
	    	  }
	     }	  
	}	
	/*
	* Function only takes valid real numbers in the matrices
	*/	
	public void getDataInMatrix(int matrixNum) {
		System.out.println("\nEnter data in matrix " + matrixNum + " in row major order ");
		try {
			int i,j;
			for( i = 0 ; i < dimension ; i ++ ) {
				for( j = 0 ; j < dimension ; j++ ) {
					System.out.println("Enter data for element(" + i + "," + j + ") ::");
					if ( matrixNum == 1) 
						matrix1[i][j] = StrassenAlgorithm.getNumber(this.br, false);
					else
						matrix2[i][j] = StrassenAlgorithm.getNumber(this.br, false);	
				}
			}
		}
		catch(Exception iex) {
		}		
	}
	/*
	* Matrix multiplication function
	*/
	public int[][] MatrixMultiply(int A[][], int B[][])
	{
		int result[][] = new int[midDimension][midDimension];	
		for (int i = 0; i < midDimension; i++)
		{
			for (int j = 0; j < midDimension; j++)
			{
				result[i][j] = 0;
				for (int k = 0; k < midDimension; k++)
				{
					result[i][j] += A[i][k]*B[k][j];
				}
			}
		}
		return result;
	}	
	/*
	Matrix addition/subtraction function
	operationToDo
	0 : subtraction
	1 : addition
	*/
	public int[][] MatrixOperation(int A[][], int B[][], int operationToDo) {
		int result[][] = new int[midDimension][midDimension];
		int i,j;
		switch(operationToDo) {
			case 0:
				for( i = 0; i < midDimension; i++){
					for( j = 0; j < midDimension; j++){
						result[i][j] = A[i][j] - B[i][j];
					}		
				}	
				break;
			case 1:
				for( i = 0; i < midDimension; i++){
					for( j = 0; j < midDimension; j++){
						result[i][j] = A[i][j] + B[i][j];
					}		
				}				
				break;
			default :
				System.out.println("Invalid operation !...");
			
		}
		return result;
	}	
	/*
	* Creates a subArray out of matrix1 or matrix2, startIndex and startColIndex are taken as specified
	* Used to create arrays A11, A12, A21, A22, B11, B12, B21 and B22
	*/
	public int[][] CreateSubArray(int A[][], int startRowIndex, int startColIndex ) {
		int rowMax = startRowIndex + midDimension;
		int colMax = startColIndex + midDimension;
		int row =0,column =0;
		int result[][] = new int[midDimension][midDimension];
		try {
			for ( int i = startRowIndex ; i <  rowMax; i++) {
				column = 0;
				for(int j= startColIndex; j <  colMax; j++) {
					result[row][column] = A[i][j];
					column++;
				}
				row++;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}	
		return result;
	}
	/*
	* Merge C11,C12,C21,C22 into matrixResult[][] array
	*/
	public void MergeMatrices(int C[][], int startRowIndex, int startColIndex) {
		int rowMax = startRowIndex + midDimension;
		int colMax = startColIndex + midDimension;
		int row =0,column =0;
		try {
			for ( int i = startRowIndex ; i <  rowMax; i++) {
				column = 0;
				for(int j= startColIndex; j <  colMax; j++) {
					matrixResult[i][j] = C[row][column];
					column++;
				}
				row++;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}	
	}
	/*
	*
	*/
	public void printMatrix(int A[][], boolean bPrintToConsole) {
		StringBuilder sText = new StringBuilder();
		int dimension = A.length;
		sText = sText.append("*************");
		sText = sText.append(System.lineSeparator());
		for ( int i = 0 ; i < dimension; i ++) {
			for ( int j = 0 ; j < dimension; j ++) {
				sText = sText.append(A[i][j] + " ");
			}	
			sText = sText.append(System.lineSeparator());
		}	
		sText = sText.append("*************");
		if (bPrintToConsole)
			System.out.println(sText);
		if (bLogFile)
			textToLog = textToLog.append(sText);
	}
	/*
	* 
	*/
	public void MultiplicationByStrassenMethod() {
		int S1[][], S2[][], S3[][], S4[][], S5[][], S6[][], S7[][], S8[][], S9[][], S10[][];
		int P1[][], P2[][], P3[][], P4[][], P5[][], P6[][], P7[][];
		int A11[][],A12[][],A21[][],A22[][], B11[][], B12[][], B21[][], B22[][];
		int C11[][],C12[][],C21[][],C22[][];
		A11 = CreateSubArray(matrix1, 0 , 0);
		A12 = CreateSubArray(matrix1, 0 , midDimension);		
		A21 = CreateSubArray(matrix1, midDimension, 0);
		A22 = CreateSubArray(matrix1, midDimension, midDimension);						
		
		B11 = CreateSubArray(matrix2, 0 , 0);
		B12 = CreateSubArray(matrix2, 0, midDimension);		
		B21 = CreateSubArray(matrix2, midDimension, 0);
		B22 = CreateSubArray(matrix2, midDimension, midDimension);
		
		if (bLogFile) {
			textToLog = textToLog.append("*************A11 matrix");
			printMatrix(A11,false);
			textToLog = textToLog.append("A12 matrix");
			printMatrix(A12,false);
			textToLog = textToLog.append("A21 matrix");
			printMatrix(A21,false);
			textToLog = textToLog.append("A22 matrix");
			printMatrix(A22,false);
			
			textToLog = textToLog.append("B11 matrix");	
			printMatrix(B11,false);
			textToLog = textToLog.append("B12 matrix");
			printMatrix(B12,false);
			textToLog = textToLog.append("B21 matrix");
			printMatrix(B21,false);
			textToLog = textToLog.append("B22 matrix");
			printMatrix(B22,false);
		}
		System.out.println("Sub matrices created...");	
	    S1 = MatrixOperation(B12 , B22, 0); //B12-B22
		S2 = MatrixOperation(A11 , A12, 1); //A11+A12
		S3 = MatrixOperation(A21 , A22, 1); //A21+A22
		S4 = MatrixOperation(B21 , B11, 0); //B21-B11
		S5 = MatrixOperation(A11 , A22, 1); //A11 + A22
		S6 = MatrixOperation(B11 , B22, 1); //B11 + B22
		S7 = MatrixOperation(A12 , A22, 0); //A12 - A22
		S8 = MatrixOperation(B21 , B22, 1); //B21 + B22
		S9 = MatrixOperation(A11 , A21, 0); //A11 - A21
		S10= MatrixOperation(B11 , B12, 1); //B11 + B12*/
		if (bLogFile) {		
			textToLog = textToLog.append("S1 matrix");
			printMatrix(S1,false);
			textToLog = textToLog.append("S2 matrix");
			printMatrix(S2,false);
			textToLog = textToLog.append("S3 matrix");
			printMatrix(S3,false);
			textToLog = textToLog.append("S4 matrix");
			printMatrix(S4,false);
			textToLog = textToLog.append("S5 matrix");
			printMatrix(S5,false);
			textToLog = textToLog.append("S6 matrix");
			printMatrix(S6,false);
			textToLog = textToLog.append("S7 matrix");		
			printMatrix(S7,false);
			textToLog = textToLog.append("S8 matrix");
			printMatrix(S8,false);
			textToLog = textToLog.append("S9 matrix");
			printMatrix(S9,false);
			textToLog = textToLog.append("S10 matrix");
			printMatrix(S10,false);	
		}
		System.out.println("S matrices created...");	
		P1 = MatrixMultiply(A11,S1); //7 multiplications in here
		P2 = MatrixMultiply(S2,B22);
		P3 = MatrixMultiply(S3,B11);
		P4 = MatrixMultiply(A22,S4);
		P5 = MatrixMultiply(S5,S6);
		P6 = MatrixMultiply(S7,S8);
		P7 = MatrixMultiply(S9,S10);
		if (bLogFile) {
			textToLog = textToLog.append("P1 matrix");
			printMatrix(P1,false);
			textToLog = textToLog.append("P2 matrix");
			printMatrix(P2,false);
			textToLog = textToLog.append("P3 matrix");
			printMatrix(P3,false);
			textToLog = textToLog.append("P4 matrix");
			printMatrix(P4,false);
			textToLog = textToLog.append("P5 matrix");
			printMatrix(P5,false);
			textToLog = textToLog.append("P6 matrix");
			printMatrix(P6,false);
			textToLog = textToLog.append("P7 matrix");
			printMatrix(P7,false);	
		}
		System.out.println("P matrices created...");		
		C11= MatrixOperation( MatrixOperation(P5 , P6 ,1 ), MatrixOperation(P4 , P2 , 0) , 1 );//P5 + P6 + P4 - P2
		C12= MatrixOperation(P1, P2, 1); //P1 + P2
		C21= MatrixOperation(P3, P4, 1); //P3 + P4
		C22= MatrixOperation(MatrixOperation(P5 , P1 ,1 ), MatrixOperation(P3 , P7 , 1) , 0); //P5 + P1 -(P3 + P7)
		if (bLogFile) {		
			textToLog = textToLog.append("C11 matrix");
			printMatrix(C11,false);
			textToLog = textToLog.append("C12 matrix");
			printMatrix(C12,false);
			textToLog = textToLog.append("C21 matrix");
			printMatrix(C21,false);
			textToLog = textToLog.append("C22 matrix");
			printMatrix(C22,false);
		}	
		System.out.println("Sub-matrices of result created...Results being merged now");
		MergeMatrices(C11,0,0);
		MergeMatrices(C12,0,midDimension);
		MergeMatrices(C21,midDimension,0);
		MergeMatrices(C22,midDimension,midDimension);
		if (bLogFile) {
			textToLog = textToLog.append("Final matrix");
		}
		printMatrix(matrixResult,true);
		if (bLogFile)
			LogIntoFile(textToLog.toString());
	}	

}	
 
/*
Algorithm follows simple divide-and-conquer strategy to achieve O(logn) complexity
For understanding DaC strategy, consult CLRS book
Using dynamic programming, computations can be reduced : This will be investigated in another program
*/

public class StrassenAlgorithm {
	
	public static int getNumber(BufferedReader br, boolean bDimension) throws IOException{
		int nResult = 0;
		String inputNumber;
		String regex = ( bDimension == true) ? "[0-9]+" : "^-?[0-9]+$"; 
		while ( true ) { 
			inputNumber	= br.readLine();	
			if ( inputNumber.matches(regex) ) {
				nResult = Integer.parseInt(inputNumber);
				if (bDimension) {
					if (nResult >= 2)
						break;
				}
				else 
					break;	
				System.out.println("\nPlease enter a valid number ! Try again...");	
			}	
			else {
				System.out.println("\nPlease enter a valid number ! Try again...");
			}
				
		}
		return nResult;
	}		
	public static void main(String []args) throws IOException {
		String inputNumber;
		int dimension;
		boolean bLogFile = false;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StrassenMultiplier sMult = new StrassenMultiplier(br);
		try {
			System.out.println("Strassen's algorithm works only on square matrices .");
			System.out.println("Usage : pass 'true' as parameter to generate detailed log file of the calculations. Default" 
								+ " behavior is not to create the log file");
			try {
				if (args.length >= 1) {
					bLogFile = args.length >= 1 ? (Boolean.parseBoolean(args[0])) : false;
				}	
			}
			catch(Exception bEx) {
				bEx.printStackTrace();
			}		
			System.out.println("Enter the dimensions for matrix  :");
			dimension 	= StrassenAlgorithm.getNumber(br, true);
			sMult.setLogOptions(bLogFile);
			sMult.setDimensions(dimension);
			sMult.getDataInMatrix(1);
			sMult.getDataInMatrix(2);
			sMult.MultiplicationByStrassenMethod();			
		}
		catch(Exception ex) {
			System.out.println("Exception occurred in input process..." + ex.getMessage());
		}	
		finally {
			try {
				br.close();
			}
			catch(Exception ex2) {
				System.out.println("Exception occurred in bufferedReader close process..." );
			}	
		}		
	}

}
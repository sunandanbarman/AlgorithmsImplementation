import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class FindLCS {
	private String str1, str2;
	private String LCS_Arrow[][];
	FindLCS(String str1, String str2) {
		this.str1 = str1;
		this.str2 = str2;
	}	
	/**
	* Finds the length of the LCS, also fills the LCS_Arrow array used to print the LCS
	* @param : void
	* Return : length of the longest subsequence
	*/
	public int FindLCSLength() {
		int i,j;
		int m = str1.length();
		int n = str2.length();
		int c[][] = new int[m+1][n+1];
		LCS_Arrow = new String[m][n];
		for (i = 1 ; i <= m ; i++) {
			c[i][0] = 0;	
		}	
		for ( j = 1 ; j <= n ; j++) {
			c[0][j] = 0;	
		}
		for( i = 0 ; i < m ; i++) {
			for ( j = 0 ; j < n; j++) {
				if ( str1.charAt(i) == str2.charAt(j)) {
					c[i+1][j+1] = c[i][j] + 1;
					LCS_Arrow[i][j] = "LD"; //Left Diagonal	
				}
				else if ( c[i][j+1] >= c[i+1][j]) {
					c[i+1][j+1] = c[i][j+1];
					LCS_Arrow[i][j] = "UP"; //Upper element
				}
				else {
					c[i+1][j+1] = c[i+1][j];
					LCS_Arrow[i][j]= "LEFT"; //Left element
				}		
			}	
		}
		return c[m][n];	
	}	
	
	public void Call_PrintLCSFunction() {
		Print_LCS(LCS_Arrow, str1, str1.length()-1, str2.length()-1);
	}		
	/**
	* @param :
	* LCS_ArrowArray ->  the array which is recursively called to find the char to be printed
	* 
	*/
	public void Print_LCS(String[][] LCS_ArrowArray, String str1, int length_str1, int length_str2) {
		if (( length_str1 == -1 ) || ( length_str2 == -1)) {
			return;
		}
		if ( LCS_ArrowArray[length_str1][length_str2].equals("LD")) {
			Print_LCS(LCS_Arrow , str1, length_str1-1, length_str2-1); 
			System.out.print(str1.charAt(length_str1)+ " ");
		}
		else if (LCS_ArrowArray[length_str1][length_str2].equals("UP")) {
			Print_LCS(LCS_Arrow , str1, length_str1-1, length_str2);			
		}		
		else {
			Print_LCS(LCS_Arrow , str1, length_str1, length_str2-1);			
		}		
	}	
		
}	

class LongestCommonSubsequence {
	/**
	*/
	public static String GetInputString(BufferedReader br) throws IOException {
		String str = "";
		while (str.equals("")) {
			str = br.readLine();
			if ( str.equals("") ) {
				System.out.println("Please enter a valid string !");
			}	
		}
		return str;
	}	
	public static void main(String[] args) {
		String str1, str2;
		int maxLength;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Enter the first string :");	
			str1 = LongestCommonSubsequence.GetInputString(br);
			System.out.println("Enter the second string :");
			str2 = LongestCommonSubsequence.GetInputString(br);
			if (( str1.length() > 3000) || (str2.length() > 3000)) {
				System.out.println("Maximum length (5000) exceeded !Exiting...");
				//return;
			}		
			FindLCS findLCS = new FindLCS(str1, str2);
			maxLength = findLCS.FindLCSLength();
			System.out.println("maximum Length :" + maxLength);
			if (maxLength > 0 ) {
				System.out.println("Printing LCS now...");
				findLCS.Call_PrintLCSFunction();
			}	
		}
		catch(IOException ex ) {
		}
		finally {
			try {
				if ( br!= null) 
					br.close();
			}
			catch(Exception ex2) {
			}		
		}		
	}	

}
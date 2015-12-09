import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.io.IOException;

/**
* File containing demonstration of various techniques of sorting
* 1. Merge Sort
* 2. Heapsort
* 3. Insertion sort
* 4. Selection sort
* 5. Radix sort
* 6. Bucket sort
* 7. Bubble sort
* 8. Quick sort
* 9. Counting sort
* 10.Shell sort
*/

/**
* Insertion sort algorithm time complexity : Average case( theta(n^2))
*/
class InsertionSort {
	private int arr_to_sort[];
	public void setArrToSort(int arr_to_sort[]) {
		this.arr_to_sort = arr_to_sort;
	}	
	public void printMergedArray(int sortedArray[]) {
		for ( int i = 0 ; i < sortedArray.length;  i++) 
			System.out.print(sortedArray[i] + " ");
		System.out.println();
	}
	public void doSorting() {
		int j,x,arr_length = arr_to_sort.length;
		for(int i=1; i<=arr_length-1;i++) {
			x = arr_to_sort[i];
			j = i;
			while ( (j>0) && (arr_to_sort[j-1]  > x)) {
				arr_to_sort[j] = arr_to_sort[j-1];
				j--;
			}	
			arr_to_sort[j] = x;
		}	
		printMergedArray(arr_to_sort);
	}	
}	
/**
* Selection sort takes Time O(n^2) , however space complexity is only O(1)
*/
class SelectionSort {
	private int arr_to_sort[];
	public void setArrToSort(int arr_to_sort[]) {
		this.arr_to_sort = arr_to_sort;
	}	
	public void printMergedArray(int sortedArray[]) {
		for ( int i = 0 ; i < sortedArray.length;  i++) 
			System.out.print(sortedArray[i] + " ");
		System.out.println();
	}
	/**
	* swaps ith index value of arr_to_sort with jth index
	*/
	public void swap(int i, int j) {
		int swap 		= arr_to_sort[j];
		arr_to_sort[j]  = arr_to_sort[i];
		arr_to_sort[i] 	= swap;
	}	
	public void doSorting() {
		int len = arr_to_sort.length;
		int min;
		for ( int i = 0 ; i < len; i++) {
			min = i;
			for ( int j = i + 1; j < len;j++) {
				if ( arr_to_sort[j] < arr_to_sort[min] ) {
					min = j;
				}	
			}
			if ( min != i)
				swap(i,min);	
		}
		printMergedArray(arr_to_sort);
	}	
	
}	

/**
*
*/
class MergeSort {
	private int arr_to_sort[];
	public void setArrToSort(int arr_to_sort[]) {
		this.arr_to_sort = arr_to_sort;
	}	
	public void printMergedArray(int sortedArray[]) {
		for ( int i = 0 ; i < sortedArray.length;  i++) 
			System.out.print(sortedArray[i] + " ");
		System.out.println();
	}	
	/**
	* Main function to be called for the sort function
	* nHigh - pass here the number of elements to sort
	* nLow  - pass here the lowest index of the array/sub-array to sort
	*/
	public void doMerge(int A[],int B[], int nLow, int nHigh) {
		if ( nHigh - nLow < 2) // only 1 element
			return;
		//nLow shows the index from where to start
		int nMiddle = ( nLow + nHigh) /2; //nMiddle shows the number of elements
		doMerge(A,B,nLow,nMiddle);
		doMerge(A,B,nMiddle,nHigh);
		
		Sort(A,B,nLow,nMiddle,nHigh);
		CopyBack(A,B,nLow,nHigh);
	}
	/**
	*@param : A[]   - the array / sub-array to be sorted
	* 	      nLow  - the lower index of the array / sub-array
	*        nMiddle- the mid index	of the array / sub-array
	*/
	void Sort(int A[],int B[], int iBegin, int iMiddle, int iEnd) {
		int i0 = iBegin, i1 = iMiddle;
		int j;		
		// While there are elements in the left or right runs
		for (j = iBegin; j < iEnd; j++) {
			// If left run head exists and is <= existing right run head.
			if (i0 < iMiddle && (i1 >= iEnd || A[i0] <= A[i1])) {
				B[j] = A[i0];
				i0 = i0 + 1;
			} else {
				B[j] = A[i1];
				i1 = i1 + 1;    
			}
		} 		
	}
	/**
	* Copies elements from tempArray to A[]
	*@param : tempArray[], A[]
	*return : none
	*/
	void CopyBack(int A[],int B[],int beginIndex, int endIndex) {
		for ( int i = beginIndex ; i < endIndex; i++) { 
			A[i] = B[i];
		}
	}
	/**
	*
	*/
	void CallSortingFunction() {
		int[] arr_temp = new int[arr_to_sort.length]; // used to store temporary results, thus merge-sort implemented here is not "in place"
		doMerge(arr_to_sort,arr_temp,0,arr_to_sort.length);
		printMergedArray(arr_to_sort);
		
	}	
}	


class SortingImplementation {
	public static int getNumber(BufferedReader br, boolean bDimension) throws IOException{
		int nResult = 0;
		String inputNumber;
		String regex = ( bDimension == true) ? "[0-9]+" : "^-?[0-9]+$"; 
		while ( true ) { 
			inputNumber	= br.readLine();	
			if ( inputNumber.matches(regex) ) {
				nResult = Integer.parseInt(inputNumber);
				if (bDimension) {
					if (nResult >= 0)
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
	
	public static void main(String[] args) { 
		int elem = 0, dimension;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the number of elements to add  :");
		try {
			dimension = getNumber(br,true);
			//dimension = 1;;
			if ( dimension == 0) {
				System.out.println("Array size is 0 ! Exiting...");
				return;
			}
			if ( dimension > 200) {
				System.out.println("Array size exceeds limit ( 200) ! Exiting...");
				return;
			}		
			int nCtr   = 0;
			int[] inputArray =  new int[dimension];
			System.out.println("Enter the " + dimension + " elements :");
			while ( nCtr < dimension ) { 	
				inputArray[nCtr] = getNumber(br,false);
				nCtr++;
			}
			
			
			MergeSort mergeSort = new MergeSort();
			mergeSort.setArrToSort(inputArray);
			mergeSort.CallSortingFunction();
			//SortingImplementation.printMergedArray(inputArray);
			
			SelectionSort sort = new SelectionSort();
			sort.setArrToSort(inputArray);
			sort.doSorting();
			
			InsertionSort insertSort = new InsertionSort();
			insertSort.setArrToSort(inputArray);
			insertSort.doSorting();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally 
		{
			try {	
				br.close();
			}
			catch(Exception ex2) {
			}		
		}
	}
	
}
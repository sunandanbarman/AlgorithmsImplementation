/****/
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.io.IOException;
import java.io.BufferedReader;
import java.math.*;
/**
* File containing demonstration of various techniques of sorting
* 1. Merge Sort *
* 2. Heapsort *
* 3. Insertion sort *
* 4. Selection sort *
* 5. Radix sort
* 6. Bucket sort
* 7. Bubble sort *
* 8. Quick sort *
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

class QuickSort {
	
	private int arr_to_sort[];
	private int partitionIndex;
	public void setArrToSort(int arr_to_sort[]) {
		this.arr_to_sort = arr_to_sort;
	}	
	public void printMergedArray(int sortedArray[]) {
		for ( int i = 0 ; i < sortedArray.length;  i++) 
			System.out.print(sortedArray[i] + " ");
		System.out.println();

	}
	//swaps the elements and returns the partition element required for recursion calls
	private int partition(int arr[],int left,int right) {
		int index = (right + left) / 2; // choosing a[n] as pivot element
		while(left < right) {
			while (arr[left] < arr[index])
				left++;
			while(arr[right] > arr[index])
				right--;
			int temp;
			if ( left < right ) { // if ordering was correct, then left = right would happen
			//Since left < right, means some element is out of order
				temp = arr[right];
				arr[right] = arr[left];
				arr[left] = temp;
				left++;
				right--;	
			}
			
		}
		return left;
	}	
	
	private void quicksort(int low,int high) {
		int partitionIndex = partition(arr_to_sort,low,high);
		if ( low < partitionIndex-1) {
			quicksort(low,partitionIndex-1);
		} else if (high > partitionIndex) {
			quicksort(partitionIndex+1,high);
		}
	}
	public void doSorting() {
		quicksort(0,arr_to_sort.length-1);
		printMergedArray(arr_to_sort);
	}
}

/**
* Build a max heap out of the unsorted array in O(n) time
* Then pick the root element, this is the last element
* Now call Max_heapify which creates the heap out of remaining elements in O(log n) time
* This runs total of n times. Thus runtime is O(n *log n) time 
*/
class HeapSort {
	private int arr_to_sort[];
	public void setArrToSort(int arr_to_sort[]) {
		this.arr_to_sort = arr_to_sort;
	}	
	public void printMergedArray(int sortedArray[]) {
		for ( int i = 0 ; i < sortedArray.length;  i++) 
			System.out.print(sortedArray[i] + " ");
		System.out.println();
	}
	public void max_heapify_recursive(int arr[],int index,int end) {
		int left 	= findLeftIndex(index);
		int right	= findRightIndex(index);
		int largest = index;
		if (left <= end) { 	
			if (arr[index] < arr[left]) {
				largest = left;
			} 
		}
			
		if (right <= end) {
			if (arr[largest] < arr[right]) {
				largest = right;
			}
		}
		if ( largest != index ) {
			//swap arr[largest] with arr[index]
			int temp     = arr[index];
			arr[index]   = arr[largest];
			arr[largest] = temp;
			max_heapify_recursive(arr,largest,arr.length-1);
		}
		
 	}
	
	public void max_heapify(int arr[],int start,int end) {
		int left = findLeftIndex(start);
		int right= findRightIndex(start);
		int root = start, swap,largest;
		while(left <= end) {
			if (arr[left] > arr[root]) {
				largest = left;
			} else  { 
				largest = root;
			}
			if(right <= end ) {
				if (arr[right] > arr[largest]) {
					largest = right;
				}
			}
			if (largest != root) {
				swap = arr[largest];
				arr[largest] = arr[root];
				arr[root] = swap;
				root = largest;
				left = findLeftIndex(root);
				right= findRightIndex(root);
			} else { 
				break;
			}
		}
	}
	public int findParentIndex(int idx) {
		if ( idx % 2 == 1) // odd , it must be left child 
			return (int)Math.floor(idx -1/2);
		else	 // even, right child
			return (int)Math.floor(idx -2/2);
	}
	public int findLeftIndex(int idx) {
		return (idx*2 + 1);
	}
	public int findRightIndex(int idx) {
		return (idx*2 + 2);
	}
	public void create_max_heap(int arr[]) {
		for(int i= (findParentIndex(arr.length)-1); i>= 0; i--) {
			max_heapify(arr,i,arr.length-1);
		}
	}
	public void doHeapSort() {
		int swap;
		int size= arr_to_sort.length-1;
		for(int i=arr_to_sort.length-1; i>=1; i--) {
			swap = arr_to_sort[0];
			arr_to_sort[0] = arr_to_sort[i];
			arr_to_sort[i] = swap; //exchange arr_to_sort[i] with arr_to_sort[0]
			size--;
			max_heapify(arr_to_sort,0,size);
 		}
	}
	public void doSorting() {
		create_max_heap(arr_to_sort);
		System.out.println("Max heap created is :");
		printMergedArray(arr_to_sort);
		doHeapSort();
		printMergedArray(arr_to_sort);
	}	
	
}

class BubbleSort {
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
	* Simple bubble sort does not have any optimization
	* Here we attempt some optimization, by using the fact that the last swapped index can be used
	* as the limit for next iteration i.e. the last "i" that is swapped means that all elements > i 
	* are in correct position
	*/
	public void doSorting() {
		boolean swapped = false;
		int swap;
		int newn;
		int n = arr_to_sort.length;
		do {
			swapped = false;
			newn = 0;
			for(int i=0; i< n-1;i++) {
				if(arr_to_sort[i] > arr_to_sort[i+1]) {
					swap = arr_to_sort[i+1];
					arr_to_sort[i+1] = arr_to_sort[i];
					arr_to_sort[i]   = swap;
					newn = n;
					swapped  = true;
				}
			}
			n = newn; //check elements only till "newn"
			//n--; //after every pass, the nth element is sorted, we need not check it again
		} while (swapped);
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
			
			System.out.println("With merge sort");
			MergeSort mergeSort = new MergeSort();
			mergeSort.setArrToSort(inputArray);
			mergeSort.CallSortingFunction();
			System.out.println("****************");
			
			System.out.println("With selection sort");
			SelectionSort sort = new SelectionSort();
			sort.setArrToSort(inputArray);
			sort.doSorting();
			System.out.println("****************");
			
			System.out.println("With insertion sort");
			InsertionSort insertSort = new InsertionSort();
			insertSort.setArrToSort(inputArray);
			insertSort.doSorting();
			System.out.println("****************");
			
			System.out.println("With quick sort");
			QuickSort quick = new QuickSort();
			quick.setArrToSort(inputArray);
			quick.doSorting();
			System.out.println("****************");
			
			System.out.println("With heap sort");
			HeapSort heapSort = new HeapSort();
			heapSort.setArrToSort(inputArray);
			heapSort.doSorting();
			System.out.println("****************");
			
			System.out.println("With bubble sort");
			BubbleSort bubble = new BubbleSort();
			bubble.setArrToSort(inputArray);
			bubble.doSorting();
			System.out.println("****************");
			
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
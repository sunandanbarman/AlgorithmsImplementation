class Sort {
	private int arr_to_sort[];
	public void setArrToSort(int arr_to_sort[]) {
		this.arr_to_sort = arr_to_sort;
	}	
	public void printMergedArray(int sortedArray[]) {
		for ( int i = 0 ; i < sortedArray.length;  i++) 
			System.out.print(sortedArray[i] + " ");
		System.out.println();
	}
	public void swap(int i,int j) {
		int swap;
		swap = arr_to_sort[j];
		arr_to_sort[j] = arr_to_sort[i];
		arr_to_sort[i] = swap;
	}
	public int partition(int low,int high) { //low and high must be indices
		int i = low, j = high;
		int partition = (low + high)  / 2;
		System.out.println(low + " :  " +  high);
		while ( i < j) {
			while ( arr_to_sort[i] < arr_to_sort[partition])
				i++;
			while(arr_to_sort[j] > arr_to_sort[partition])
				j--;
			if ( i < j) {
				swap(i,j);
				i++;
				j--;
			} 
		}
		return i; 
	}
	public void doSorting() {
		if ((arr_to_sort.length == 0 ) || (arr_to_sort.length == 1))
			return;
		int[] stack = new int[arr_to_sort.length];
		int low = 0;
		int high= arr_to_sort.length-1;
		int top = -1;
		stack[++top] = low;
		stack[++top] = high;
			
		while( top >=0 ) {
			high = stack[top--];
			low  = stack[top--];
			int p = partition(low,high);
			
			if ( p -1 > low) {
				stack[++top] = low;
				stack[++top] = p-1;
			}
			if ( p+1 < high) {
				stack[++top] = p+1;
				stack[++top] = high;
			}
			
		}
		printMergedArray(arr_to_sort);
	}
}
class NonRecQuickSort {


	public static void main(String[] args) {
		Sort sort = new Sort();
		int[] arr = {5,4,3,2,1};
		sort.setArrToSort(arr);
		sort.doSorting();
	}
}
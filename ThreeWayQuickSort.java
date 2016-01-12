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
	private void sort(int[] arr,int low, int high) {
		if ( low >= high)
		  return;
		int lt = low, gt = high , i = low;
		int partition = arr[low];
		while ( i <= gt) {
			
			if (arr[i]  < partition) { 
				swap(lt,i) ;  
				lt++; 
				i++;
			}
			else if (arr[i] > partition) {
				swap(i,gt);
				gt--;
			}
			else {
				i++;
			}
			
		}
		sort(arr,low,lt-1);
		sort(arr,gt+1,high);
		
	}
	public void doSorting() {
		sort(arr_to_sort,0,arr_to_sort.length-1);
		printMergedArray(arr_to_sort);
	}
}	


class ThreeWayQuickSort {
	public static void main(String[] args) {
		Sort sort = new Sort();
		int[] arr = {5,4,3,2,1};
		sort.setArrToSort(arr);
		sort.doSorting();
	}
}
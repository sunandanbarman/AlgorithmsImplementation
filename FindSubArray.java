import java.util.LinkedList;
import java.util.Queue;

/**
* The objective of the algorithm is to find a subarray of maximal length within given array with sum <= k.
E.g. arr = {3,1,2,1} and k = 4, the maximal subarray is {1,2,1} 
**/
public class FindSubArray {	
	public static int findMaxLength(int[] arr,int k) {
		if (arr.length == 0) {
			return 0;
		}
		int runningSum = 0;
		Queue<Integer> q = new LinkedList<>();
		int maxLengthSoFar = Integer.MIN_VALUE;
		int length = 0;
		for(int i=0; i < arr.length;i++) {
			runningSum += arr[i];
			q.add(arr[i]);
			while(runningSum > k) {
				if (!q.isEmpty()) {
					int x = q.remove();
					runningSum = runningSum- x;
					
				} else {
					break;
				}
			}
			if (!q.isEmpty()) {
				length = q.size();
			} else {
				length = 0;
			}
			
			if (length > maxLengthSoFar) {
				maxLengthSoFar = length;
			}
		}
		return maxLengthSoFar;
	}
	public static void main(String []args) {
		int[] arr = new int[]{3,1,2,1};
		System.out.println(findMaxLength(arr,4));
	}
}
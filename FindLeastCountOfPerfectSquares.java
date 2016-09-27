import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class FindLeastCountOfPerfectSquares {
	public static int findCount(int n) {
        HashSet<Integer> set = new HashSet<>();
        Queue<Integer> q  = new LinkedList<>();
        
        q.offer(n);
        int level = 1;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int j=0; j < size; j++) {
                int num = q.poll();
                int curr= (int)Math.sqrt(num);
                if (curr * curr == num) {
                    return level;
                }
                for(int i=curr; i >0; i--) {
                    if (set.add(num - i *i)) {
                        q.offer(num - i * i);
                    }
                }
            }
            level++;
        }
        return level;
	}
	public static void main(String []args) {
		System.out.println(findCount(19));
	}
}

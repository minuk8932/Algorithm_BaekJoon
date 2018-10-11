import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Boj7469 {
	private static int[] tree = null;
	
	private static final String NEW_LINE = "\n";
	private static final int INF = 1_000_000_001;
	
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int s = 1;
		
		while(s < n) s <<= 1;
		
		tree = new int[s * 2];
		Arrays.fill(tree, INF);
		
		for(int i = s; i < s + n; i++) tree[i] = sc.nextInt();
		for(int i = tree.length - 1; i >= 0; i -= 2) {
			tree[i / 2] = Math.min(tree[i], tree[i] - 1);
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(m-- > 0) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int seq = sc.nextInt();
			
			sb.append(getNumber(s + from - 1, s + to - 1, seq)).append(NEW_LINE);
		}
		
//		System.out.println(sb.toString());
	}
	
	private static class Number implements Comparable<Number>{
		int element;
		
		public Number(int element) {
			this.element = element;
		}

		@Override
		public int compareTo(Number n) {
			if(this.element < n.element) return -1;
			else if(this.element > n.element) return 1;
			else return 0;
		}
	}
	
	private static int getNumber(int seg1, int seg2, int loop) {
		int num = INF;
		PriorityQueue<Number> pq = new PriorityQueue<>();
		
		while(seg1 <= seg2) {
			if(seg1 % 2 == 1) {
				num = Math.min(tree[seg1], num);
				seg1--;
			}
			
			if(seg2 % 2 == 0) {
				num = Math.min(tree[seg2], num);
				seg2--;
			}
			
			seg1 /= 2;
			seg2 /= 2;
		}
		
		while(!pq.isEmpty()) System.out.print(pq.poll().element + " ");
		System.out.println();
		
		int res = 0;
//		while(loop-- > 0) {
//			res = pq.poll().element;
//		}
		
		return res;
	}
}

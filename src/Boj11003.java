import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj11003 {
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int idx = 0;
		int loop = 0;
		
		PriorityQueue<Number> pq = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		
		while(N-- > 0) {
			int A = Integer.parseInt(st.nextToken());
			
			pq.offer(new Number(A, idx++));
			sb.append(pq.peek().num).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static class Number implements Comparable<Number>{
		int num;
		int idx;
		
		public Number(int num, int idx) {
			this.num = num;
			this.idx = idx;
		}

		@Override
		public int compareTo(Number n) {
			return this.num < n.num ? -1 : 1;
		}
	}
}

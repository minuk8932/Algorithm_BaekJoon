package baekjoon_contest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BSIS_ProblemG {
	private static final String NEW_LINE = "\n";
	
	private static final int INF = 500_001;
	
	private static Water[] w = new Water[INF];
	private static Range[] r = new Range[INF];
	
	private static long[] values = new long[INF];
	private static int[] seq = new int[INF];
	
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i < N + 1; i++) {
			w[i] = new Water(-i, Long.parseLong(st.nextToken()) + 1L);
		}
		
		r[0] = new Range(-1, -1);
		
		for(int i = 1; i < Q + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());

			r[T] = new Range(L, R);
			seq[i] = T;
		}
		
		w[0] = new Water(0, 0);
		
		for(int i = 1; i < Q + 1; i++) {
			long cnt = 0;
			w[0].pos++;
			
			for(int j = 1; j < N + 1; j++) {
				if(Math.abs(w[j - 1].pos - w[j].pos) >= w[j].dist) w[j].pos = w[j - 1].pos - 1;
			}
			
//			Range ran = r.remove(0);
			
			for(int j = 0; j < N + 1; j++) {				
				if(w[j].pos >= r[i].left && w[j].pos <= r[i].right) cnt++;
				
				System.out.print(w[j].pos + " ");
			}
			
			values[i] = cnt;
//			sb.append(cnt).append(NEW_LINE);
			System.out.println();
		}
		
		for(int i = 1; i < Q + 1; i++) {
			sb.append(values[seq[i]]).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static class Range{
		int left;
		int right;
		
		public Range(int left, int right){
			this.left = left;
			this.right = right;
		}
	}
	
	private static class Water{
		int pos;
		long dist;
		
		public Water(int pos, long dist) {
			this.pos = pos;
			this.dist = dist;
		}
	}
}

package baekjoon_contest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BSIS_ProblemG {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		Water[] w = new Water[N + 1];
		w[0] = new Water(0, 0);
		st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i < N + 1; i++) {
			w[i] = new Water(-i, Integer.parseInt(st.nextToken()));
		}
		
		StringBuilder sb = new StringBuilder();
		
		Range[] r = new Range[Q + 1];
		int[] values = new int[N + 1];
		
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());

			r[T] = new Range(R, L);
		}
		
		for(int i = 1; i < Q + 1; i++) {
			
		}
		
		
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
		int move;
		
		public Water(int pos, int move) {
			this.pos = pos;
			this.move = move;
		}
	}
}

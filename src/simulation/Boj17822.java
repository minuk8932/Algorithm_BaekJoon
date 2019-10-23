package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17822번: 원판 돌리기
 *
 *	@see https://www.acmicpc.net/problem/17822/
 *
 */
public class Boj17822 {
	private static int[][] circle;
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static class Query{
		int x;
		int d;
		int k;
		
		public Query(int x, int d, int k) {
			this.x = x;
			this.d = d;
			this.k = k;
		}
	}
	
	private static class Pair{
		int row;
		int col;
		
		public Pair(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		circle = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				circle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Query[] q = new Query[T];
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			q[i] = new Query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) % M);
		}
		
		rotation(N, M, q);
		System.out.println(getSum());
	}
	
	private static void rotation(int n, int m, Query[] query) {
		LinkedList<Pair> remove = new LinkedList<>();
		
		for(Query q: query) {			
			rotate(n, q);
			
			for(int row = 0; row < n; row++) {
				for(int col = 0; col < m; col++) {
					if(circle[row][col] == 0) continue;
					int current = circle[row][col];
					boolean flag = false;
					
					for(final int[] DIRECTION: DIRECTIONS) {
						int nextRow = row + DIRECTION[ROW];
						int nextCol = col + DIRECTION[COL];
						
						if(nextRow < 0 || nextRow >= n) continue;
						if(nextCol < 0) nextCol += m;
						else if(nextCol >= m) nextCol %= m;
						
						if(current == circle[nextRow][nextCol]) {			// save remove number
							remove.add(new Pair(nextRow, nextCol));
							flag = true;
						}
					}
					
					if(flag) remove.add(new Pair(row, col));
				}
			}
			
			if(remove.size() == 0) valueModify();							// if not get avg
			
			while(!remove.isEmpty()) {
				Pair p = remove.remove();
				circle[p.row][p.col] = 0;
			}
		}
	}
	
	private static int getSum() {
		int sum = 0;
		
		for(int i = 0; i < circle.length; i++) {
			for(int j = 0; j < circle[i].length; j++) {
				sum += circle[i][j];
			}
		}
		
		return sum;
	}
	
	private static void rotate(int n, Query q) {
		int loop = q.x;
		int time = loop;
		
		while(loop <= n) {					// rotate each dishes
			int idx = loop - 1;
			
			if(q.d == 0) move(q.k, idx);	// cw
			else move(-q.k, idx);			// ccw
			
			loop += time;
		}
	}
	
	private static void move(int adder, int idx) {
		int[] tmp = new int[circle[idx].length];
		
		for(int i = 0; i < circle[idx].length; i++) {
			int val = i + adder;
			
			val %= circle[idx].length;
			if(val < 0) val += circle[idx].length;
			tmp[val] = circle[idx][i];						// rotate position decision
		}

		for(int i = 0; i < circle[idx].length; i++) {
			circle[idx][i] = tmp[i];
		}
	}
	
	private static void valueModify() {
		double avg = 0.0;
		int count = 0;
		
		for(int idx = 0; idx < circle.length; idx++) {			// get sum
			for(int i = 0; i < circle[idx].length; i++) {
				if(circle[idx][i] == 0) continue;
				avg += circle[idx][i];
				count++;
			}
		}
			
		avg /= count;
			
		for(int idx = 0; idx < circle.length; idx++) {
			for(int i = 0; i < circle[idx].length; i++) {
				if(circle[idx][i] == 0) continue;
				
				if(circle[idx][i] < avg) circle[idx][i]++;
				else if(circle[idx][i] > avg) circle[idx][i]--;
			}
		}
	}
}

package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16235번: 나무 재테크
 *
 *	@see https://www.acmicpc.net/problem/16235/
 *
 */
public class Boj16235 {
	private static LinkedList<Trees> tree = new LinkedList<>();
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static class Trees{
		int row;
		int col;
		int age;
		
		public Trees(int row, int col, int age) {
			this.row = row;
			this.col = col;
			this.age = age;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] A = new int[N + 1][N + 1];
		int[][] fix = new int[N + 1][N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j < N + 1; j++) {
				A[i][j] = 5;
				fix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			tree.add(new Trees(x, y, z));
		}
		
		System.out.println(getSurvivor(N, K, A, fix));
	}
	
	private static int getSurvivor(int n, int k, int[][] a, int[][] adder) {
		LinkedList<Trees> dead = new LinkedList<>();
		LinkedList<Trees> surv = new LinkedList<>();
		
		while(k-- > 0) {
			while(!tree.isEmpty()) {								// 봄: 죽일것 죽이고, 산것은 나이 +1	
				Trees t = tree.remove();
				
				if(a[t.row][t.col] < t.age) {
					dead.add(t);
				}
				else {
					surv.add(new Trees(t.row, t.col, t.age + 1));
					a[t.row][t.col] -= t.age;
				}
			}
			
			while(!dead.isEmpty()) {								// 여름: (죽인 것의 나이 / 2) 만큼 양분 +
				Trees t = dead.remove();
				a[t.row][t.col] += (t.age / 2);
			}
			
			while(!surv.isEmpty()) {								// 가을: 증식 가능한 친구들 증식
				Trees t = surv.remove();
				tree.add(t);
				if(t.age % 5 != 0) continue;
					
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = t.row + DIRECTION[ROW];
					int nextCol = t.col + DIRECTION[COL];
					
					if(nextRow < 1 || nextRow > n || nextCol < 1 || nextCol > n) continue;
					tree.addFirst(new Trees(nextRow, nextCol, 1));
				}
			}
			
			if(tree.isEmpty()) return 0;
			
			for(int row = 1; row < n + 1; row++) {					// 겨울: 로봇이 양분 채움
				for(int col = 1; col < n + 1; col++) {
					a[row][col] += adder[row][col];
				}
			}
		}
		
		return tree.size();			// 살아남은 나무 수
	}
}

package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16235번: 나무 재태크
 *
 *	@see https://www.acmicpc.net/problem/16235/
 *
 */
public class Boj16235 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static class Trees implements Comparable<Trees>{
		int row;
		int col;
		int age;
		
		public Trees(int row, int col, int age) {
			this.row = row;
			this.col = col;
			this.age = age;
		}

		@Override
		public int compareTo(Trees t) {
			return this.age < t.age ? -1 : 1;
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
		
		PriorityQueue<Trees> tree = new PriorityQueue<>();
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			tree.add(new Trees(x, y, z));
		}
		
		System.out.println(getSurvivor(N, K, A, fix, tree));
	}
	
	private static int getSurvivor(int n, int k, int[][] a, int[][] adder, PriorityQueue<Trees> pq) {		
		while(k-- > 0) {
			int size = pq.size();
			LinkedList<Trees> surv = new LinkedList<>();
			LinkedList<Trees> isDead = new LinkedList<>();
			
			while(size-- > 0) {						// 봄: 죽일것 죽이고, 산것은 나이 +1
				Trees current = pq.poll();
				
				if(a[current.row][current.col] < current.age) {
					isDead.add(current);
					continue;
				}
				
				surv.add(new Trees(current.row, current.col, current.age + 1));
				a[current.row][current.col] -= current.age;
			}
			
			while(!surv.isEmpty()) {
				pq.offer(surv.remove());
			}
			
			for(Trees dead: isDead) {						// 여름: (죽인 것의 나이 / 2) 만큼 양분 +
				a[dead.row][dead.col] += (dead.age / 2);
			}
			
			size = pq.size();
			LinkedList<Trees> create = new LinkedList<>();
			
			while(size-- > 0) {								// 가을: 증식 가능한 친구들 증식
				Trees current = pq.poll();
				create.add(current);
				if(current.age % 5 != 0) continue;
					
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					
					if(nextRow < 1 || nextRow > n || nextCol < 1 || nextCol > n) continue;
					create.add(new Trees(nextRow, nextCol, 1));
				}
			}
			
			while(!create.isEmpty()) {
				pq.offer(create.remove());
			}
			
			if(pq.isEmpty()) return 0;
			
			for(int row = 1; row < n + 1; row++) {			// 겨울: 로봇이 양분 채움
				for(int col = 1; col < n + 1; col++) {
					a[row][col] += adder[row][col];
				}
			}
		}
		
		return pq.size();			// 살아남은 나무 수
	}
}

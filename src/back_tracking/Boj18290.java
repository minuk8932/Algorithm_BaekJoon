package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 18290번: NM과 K 1
 *
 *	@see https://www.acmicpc.net/problem/18290/
 *
 */
public class Boj18290 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	private static final int INF = -1_000_000;
	
	private static int[] perm;
	private static boolean[] visit;
	
	private static int N, M, K;
	private static int max = INF;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		perm = new int[K];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N * M; i++) {
			visit = new boolean[N * M];
			
			perm[0] = i;
			backTracking(map, i, 1);			// find posssible case
		}
		
		System.out.println(max);
	}
	
	private static void backTracking(int[][] arr, int current, int count) {
		if(visit[current]) return;
		visit[current] = true;
		
		if(count == K) {
			max = Math.max(search(arr), max);	// get Max
			return;
		}
		
		for(int next = current; next < N * M; next++) {
			if(visit[next]) continue;
			perm[count] = next;
			
			backTracking(arr, next, count + 1);
			visit[next] = false;
		}
	}
	
	private static int search(int[][] arr) {
		boolean[][] check = new boolean[N][M];
		int result = 0;
		
		for(int index: perm) {
			int row = index / M;
			int col = index % M;
			
			if(check[row][col]) return INF;
			check[row][col] = true;
			result += arr[row][col];
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int adjRow = row + DIRECTION[ROW];
				int adjCol = col + DIRECTION[COL];
				
				if(adjRow < 0 || adjCol < 0 || adjRow >= N || adjCol >= M) continue;
				check[adjRow][adjCol] = true;
			}
		}
		
		return result;
	}
}

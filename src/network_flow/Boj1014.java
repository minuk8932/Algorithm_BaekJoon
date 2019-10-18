package network_flow;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 1014번: 컨닝
 *
 *	@see https://www.acmicpc.net/problem/1014/
 *
 */
public class Boj1014 {
	private static final String NEW_LINE = "\n";
	
	private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
	private static final int ROW = 0, COL = 1;
	
	private static final char EMPTY = '.';
	
	private static ArrayList<Integer>[] connected;
	private static ArrayList<Integer> target;
	private static int[] visit;
	private static int[] matchB;
	private static int[] matchA;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int C = Integer.parseInt(br.readLine());
		
		while(C-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int length = N * M;
			int x = 0;
			
			connected = new ArrayList[length];
			target = new ArrayList<>();
			
			for(int i = 0; i < length; i++) {
				connected[i] = new ArrayList<>();
			}
			
			char[][] map = new char[N][M];
			
			for(int i = 0; i < N; i++) {
				String line = br.readLine();
				
				for(int j = 0; j < M; j++) {
					map[i][j] = line.charAt(j);
				}
			}
			
			for(int row = 0; row < N; row++) {
				for(int col = 0; col < M; col++) {
					if(map[row][col] != EMPTY) {				// broken seats
						x++;
						continue;
					}
					
					int node1 = row * M + col;
					if(col % 2 == 0) target.add(node1);
						
					for(final int[] DIRECTION: DIRECTIONS) {	// flow cuts
						int adjRow = row + DIRECTION[ROW];
						int adjCol = col + DIRECTION[COL];
							
						if(adjRow < 0 || adjCol < 0 || adjRow >= N || adjCol >= M) continue;
						if(map[adjRow][adjCol] != EMPTY) continue;
						int node2 = adjRow * M + adjCol;
							
						connected[node1].add(node2);
					}
				}
			}
			
			sb.append(N * M - bipartiteMatch(N, M) - x).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int bipartiteMatch(int n, int m) {
		int total = 0;
		int count = 1;
		
		visit = new int[n * m];
		matchA = new int[n * m];
		matchB = new int[n * m];
		Arrays.fill(matchB, -1);
		Arrays.fill(visit, -1);
		
		for (int t: target) {
			count++;
	        total += recursion(t, count);
	    }
		
		return total;
	}
	
	private static int recursion(int current, int count) {
		if(visit[current] == count) return 0;
		visit[current] = count;
		
		for (int next: connected[current]) {
	        if (matchB[next] == -1 || recursion(matchB[next], count) == 1){		// isMatched?
	            matchA[current] = next;
	            matchB[next] = current;
	            
	            return 1;
	        }
	    }
		
		return 0;
	}
}

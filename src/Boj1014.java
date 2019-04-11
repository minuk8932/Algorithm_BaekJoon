import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1014 {
	private static final String NEW_LINE = "\n";
	
	private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {-1, 1}, {-1, -1}, {1, 1}, {-1, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static final char EMPTY = '.';
	
	private static ArrayList<Integer>[] connected;
	private static int[][] flow;
	private static int[][] capacity;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int C = Integer.parseInt(br.readLine());
		
		while(C-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int length = N * M * 2 + 2, src = N * M * 2, snk = N * M * 2 + 1;
			
			connected = new ArrayList[length];
			flow = new int[length][length];
			capacity = new int[length][length];
			
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
					if(map[row][col] == EMPTY) {
						for(final int[] DIRECTION: DIRECTIONS) {
							int adjRow = row + DIRECTION[ROW];
							int adjCol = col + DIRECTION[COL];
							
							if(adjRow < 0 || adjCol < 0 || adjRow >= N || adjCol >= M) continue;
							if(map[adjRow][adjCol] != EMPTY) continue;

							connected[row * M + col].add((adjRow * M + adjCol) + N * M);
							connected[(adjRow * M + adjCol) + N * M].add(row * M + col);
							capacity[row * M + col][adjRow * M + adjCol] = 1;
						}
						
						connected[src].add(row * M + col);
						connected[row * M + col].add(src);
						capacity[src][row * M + col] = 4;
						
						connected[snk].add(row * M + col);
						connected[row * M + col].add(snk);
						capacity[row * M + col][snk] = 1;
					}
				}
			}
			
			sb.append(networkFlow(N, M, map, src, snk, length)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int networkFlow(int n, int m, char[][] arr, int source, int sink, int size) {
		int result = 0;
		int[] prev = new int[size];
		int[] spread = new int[size];
		
		while(true) {
			Arrays.fill(prev, -1);
			Arrays.fill(spread, 0);
			
			Queue<Integer> q = new LinkedList<>();
			q.offer(source);
			
			while(!q.isEmpty()) {
				int current = q.poll();
				
				for(int next: connected[current]) {
					if(prev[next] == -1 && capacity[current][next] - flow[current][next] > 0) {
						prev[next] = current;
						spread[next]++;
						
						q.offer(next);
					}
				}
			}
			
			if(prev[sink] == -1) break;
			
			for(int i = sink; i != source; i = prev[i]) {
				flow[prev[i]][i]++;
				flow[i][prev[i]]--;
			}
			
			result++;
		}
		
		return result;
	}
}

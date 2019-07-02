import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1014 {
	private static final String NEW_LINE = "\n";
	
	private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {-1, 1}, {-1, -1}};
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
			
			int length = N * M * 2 + 2;
			int src = length - 2, snk = length - 1;
			
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
							int node1 = row * M + col;
							int node2 = adjRow * M + adjCol + N * M;

							connected[src].add(node1);					// 열 한개 더
							connected[node1].add(src);
							capacity[src][node1] = 4;
							
							connected[node1].add(node2);
							connected[node2].add(node1);
							capacity[node1][node2] = 1;
							
							connected[snk].add(node2);
							connected[node2].add(snk);
							capacity[node2][snk] = 1;
						}
					}
				}
			}
			
			sb.append(networkFlow(N, M, src, snk, length)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int networkFlow(int n, int m, int source, int sink, int size) {
		int result = 0;
		int[] prev = new int[size];
		
		while(true) {
			Arrays.fill(prev, -1);
			
			Queue<Integer> q = new LinkedList<>();
			q.offer(source);
			
			while(!q.isEmpty()) {
				int current = q.poll();
				
				for(int next: connected[current]) {
					if(prev[next] != -1) continue;
					
					if(capacity[current][next] - flow[current][next] > 0) {
						prev[next] = current;
						
						q.offer(next);
					}
				}
			}
			
			if(prev[sink] == -1) break;
			
			for(int i = sink; i != source; i = prev[i]) {
				flow[prev[i]][i] += 1;
				flow[i][prev[i]] -= 1;
			}
			
			result += 1;
		}
		
		return result;
	}
}

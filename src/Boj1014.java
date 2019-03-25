import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj1014 {
	private static final String NEW_LINE = "\n";
	
	private static final int[][] DIRECTIONS = {{-1, -1}, {-1, 0}, {0, 1}, {-1, 1}};
	private static final int ROW = 0, COL = 1;
	
	private static final char EMPTY = '.';
	
	private static ArrayList<Integer>[] connected;
	private static int[] flow;
	private static int[] capacity;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int C = Integer.parseInt(br.readLine());
		
		while(C-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			connected = new ArrayList[N];
			for(int i = 0; i < N; i++) {
				connected[i] = new ArrayList<>();
			}
			
			char[][] map = new char[N][M];
			
			for(int i = 0; i < N; i++) {
				String line = br.readLine();
				
				for(int j = 0; j < M; j++) {
					map[i][j] = line.charAt(j);
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] != EMPTY) continue;
					
					for(final int[] DIRECTION: DIRECTIONS) {
						int nextRow = i + DIRECTION[ROW];
						int nextCol = j + DIRECTION[COL];
						
						if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) continue;
						if(map[nextRow][nextCol] != EMPTY) continue;
						capacity[i]++;
					}
				}
			}
			
			sb.append(networkFlow(N, M, map)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int networkFlow(int n, int m, char[][] arr) {
		int minFlow = 0;
		
		
		
		return minFlow;
	}
}

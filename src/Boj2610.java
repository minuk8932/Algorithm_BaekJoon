import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2610 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static final int INF = 1_000_000;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[][] grp = new int[N + 1][N + 1];	
		for(int i = 0; i < N + 1; i++){
			Arrays.fill(grp[i], INF);
		}
		
		for(int i = 0; i < M; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			grp[from][to] = 1;
		}
		
		floydWashall(grp, N);
		
		
	}
	
	private static void floydWashall(int[][] map, int N){
		for(int v = 1; v < N + 1; v++){
			for(int s = 1; s < N + 1; s++){
				for(int e = 1; e < N + 1; e++){
					if(map[s][e] > map[s][v] + map[v][e]){
						map[s][e] = map[s][v] + map[v][e];
					}
				}
			}
		}
	}
	
}

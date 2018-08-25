import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj6135 {
	private static final int INF = 10_000_001;
	private static final String NEW_LINE = "\n";
	
	private static StringTokenizer st = null;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N + 1][N + 1];
		for(int i = 0; i < N + 1; i++) {
			Arrays.fill(map[i], INF);
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		
		String[] tc = new String[T];
		for(int i = 0; i < T; i++) {
			tc[i] = br.readLine();
		}
		
		System.out.println(floydWashall(N, T, map, tc));
	}
	
	private static String floydWashall(int N, int T, int[][] cows, String[] input){
		StringBuilder sb = new StringBuilder();
		
		for(int v = 1; v < N + 1; v++) {
			for(int s = 1; s < N + 1; s++) {
				for(int e = 1; e < N + 1; e++) {
					if(cows[s][e] > cows[s][v] + cows[v][e]) cows[s][e] = cows[s][v] + cows[v][e];
				}
			}
		}
		
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(input[i]);
			int res = cows[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
			sb.append(res == INF ? -1 : res).append(NEW_LINE);
		}
		
		return sb.toString();
	}
}

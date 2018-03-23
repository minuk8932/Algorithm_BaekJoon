import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj11562 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static final int INF = 1_000_000;
	private static final int ONE_WAY = 0;
	
	private static boolean[][] hasWay = null;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n + 1][n + 1];
		for(int i = 0; i < n + 1; i++){
			Arrays.fill(map[i], INF);
		}
		
		hasWay = new boolean[n + 1][n + 1];
		
		for(int i = 0; i < m; i++){
			st = new StringTokenizer(br.readLine(), SPACE);
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(b == ONE_WAY){
				map[u][v] = 1;
				hasWay[u][v] = true;
			}
			else{
				map[u][v] = map[v][u] = 1;
				hasWay[u][v] = hasWay[v][u] =  true;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int k = Integer.parseInt(br.readLine());
		
		while(k-- > 0){
			st = new StringTokenizer(br.readLine(), SPACE);
			int s = Integer.parseInt(st.nextToken());	//	from
			int e = Integer.parseInt(st.nextToken()); // to
			
			sb.append(floydWashall(map, n, s ,e)).append(NEW_LINE);			
		}
		
		System.out.println(sb.toString());
	}
	
	private static int floydWashall(int[][] map, int n, int start, int end){
		int cnt = 0;
		
		for(int v = 1; v < n + 1; v++){
			for(int s = 1; s < n + 1; s++){
				for(int e = 1; e < n + 1; e++){
					if(map[s][e] > map[s][v] + map[v][e]){
						map[s][e] = map[s][v] + map[v][e];
					}
					
					if(s != e && map[start][end] == INF){
						
						int sub = Math.abs(start - end);
						
						cnt = sub;
					}
				}
			}
		}
		
		return cnt;
	}
}

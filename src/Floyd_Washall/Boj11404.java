package Floyd_Washall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj11404 {
	private static final int MAX = 100_001;
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final int NO_WAY = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		int[][] city = new int[n+1][n+1];
		
		for(int i = 0; i < n+1; i++){
			for(int j = 0; j < n+1; j++){
				city[i][j] = MAX;
				
				if(i == j){
					city[i][j] = NO_WAY;
				}
			}
		}
		
		for(int i = 0; i < m; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			if(city[x][y] == MAX){
				city[x][y] = cost;
			}
			else{
				if(cost < city[x][y]){
					city[x][y] = cost;
				}
			}
		}
		
		for(int v = 1; v < n+1; v++){
			for(int s = 1; s < n+1; s++){
				for(int e = 1; e < n+1; e++){
						city[s][e] = Math.min(city[s][e], city[s][v] + city[v][e]);
				}
			}
		}
		
		for(int i = 0; i < n+1; i++){
			for(int j = 0; j < n+1; j++){
				if(city[i][j] >= MAX){
					city[i][j] = NO_WAY;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < n+1; i++){
			for(int j = 1; j < n+1; j++){
				sb.append(city[i][j]).append(SPACE);
			}
			sb.append(NEW_LINE);
		}
		System.out.println(sb.toString());
	}

}

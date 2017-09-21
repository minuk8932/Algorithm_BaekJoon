package Floyd_Washall;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj11403 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		StringTokenizer st = null;
		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine(), SPACE);
			for(int j = 0; j < N; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int v = 0; v < N; v++){
			for(int s = 0; s < N; s++){
				for(int e = 0; e < N; e++){
					map[s][e] = Math.min(0, map[s][v] + map[v][e]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++){
			
			for(int j =0; j < N; j++){
				sb.append(map[i][j]).append(SPACE);
			}
			sb.append(NEW_LINE);
		}
		System.out.println(sb.toString());
	}

}

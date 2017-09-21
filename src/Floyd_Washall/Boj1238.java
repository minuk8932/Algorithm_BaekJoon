package Floyd_Washall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1238 {
	private static final String SPACE = " ";
	private static final int LIMIT = 100_000;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		int p[][] = new int[N+1][N+1];
		
		for(int i = 1; i < N+1; i++){
			for(int j = 1; j < N+1; j++)
			if(i != j){
				p[i][j] = LIMIT;
			}
		}
		
		while(M-- > 0){
			st = new StringTokenizer(br.readLine(), SPACE);
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			
			p[from][to] = T;
		}
		
		for(int v = 1; v < N+1; v++){
			for(int s = 1; s < N+1; s++){
				for(int e = 1; e < N+1; e++){
					p[s][e] = Math.min(p[s][e], p[s][v] + p[v][e]);
				}
			}
		}
		
		int max = 0;
		
		for(int i = 1; i < N+1; i++){
			max = Math.max(max, p[i][X] + p[X][i]);
		}
		
		System.out.println(max);
		
	}

}

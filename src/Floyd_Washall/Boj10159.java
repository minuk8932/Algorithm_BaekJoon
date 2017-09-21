package Floyd_Washall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10159 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static final int LIMIT = 100_000;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[][] thing = new int[N+1][N+1];
		
		for(int i = 0; i < N+1; i++){
			for(int j = 0; j < N+1; j++){
				thing[i][j] = LIMIT;
			}
		}
		
		while(M-- > 0){
			StringTokenizer st =  new StringTokenizer(br.readLine(), SPACE);
			
			thing[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;	
		}
		
		for(int v = 1; v < N+1; v++){
			for(int s = 1; s < N+1; s++){
				for(int e = 1; e < N+1; e++){
					thing[s][e] = Math.min(thing[s][e], thing[s][v] + thing[v][e]);
				}
			}
		}
		
		
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < N+1; i++){			
			for(int j = 1; j < N+1; j++){
				if(i != j && thing[i][j] != LIMIT){
					thing[j][i] = thing[i][j];
				}
			}
		}
		
		for(int i = 1; i < N+1; i++){
			int cnt = 0;
			for(int j = 1; j < N+1; j++){
				if(i != j && thing[i][j] == LIMIT){
					cnt++;
				}
			}
			sb.append(cnt).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}

}

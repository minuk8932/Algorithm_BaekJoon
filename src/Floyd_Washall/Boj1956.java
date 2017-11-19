package Floyd_Washall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1956 {
	private static final int NO_WAY = -1;
	private static final int MAX = 10_001;
	
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int[][] path = new int[V + 1][V + 1];
		
		for(int i = 0; i < V+1; i++){
			Arrays.fill(path[i], MAX);
		}
		
		for(int i = 0; i < E; i++){
			st = new StringTokenizer(br.readLine(), SPACE);
			path[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		
		for(int via = 1; via < V + 1; via++){
			for(int s = 1; s < V + 1; s++){
				for(int e = 1; e < V + 1; e++){
					path[s][e] = Math.min(path[s][e], path[s][via] + path[via][e]);
				}
			}
		}
		
		int res = MAX;
		int chk = 0;
		
		for(int i = 1; i < V + 1; i++){
			for(int j = 1; j < V + 1; j++){
				if(path[i][j] != MAX && path[j][i] != MAX ){
					res = Math.min(res, path[i][j] + path[j][i]);
					chk++;
				}
			}
		}
		
		if(chk == 0){
			System.out.println(NO_WAY);
		}
		else{
			System.out.println(res);
		}
	}

}

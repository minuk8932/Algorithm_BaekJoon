package Floyd_Warshall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1956 {
	private static final int NO_WAY = -1;
	private static final int MAX = 1_000_000_000;
	private static final String SPACE = " ";

	private static int[][] path;
	private static int V;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		path = new int[V][V];
		
		for(int i = 0; i < V; i++){
			Arrays.fill(path[i], MAX);
		}
		
		while(E-- > 0){
			st = new StringTokenizer(br.readLine(), SPACE);
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			path[a][b] = Integer.parseInt(st.nextToken());
		}

		floydWarshall();

		int answer = getCycle();
		System.out.println(answer == MAX ? NO_WAY: answer);
	}

	private static int getCycle() {
		int cost = MAX;

		for(int i = 0; i < V; i++){
			for(int j = 0; j < V; j++){
				if(path[i][j] == MAX || path[j][i] == MAX) continue;
				cost = Math.min(cost, path[i][j] + path[j][i]);
			}
		}

		return cost;
	}

	private static void floydWarshall() {
		for(int via = 0; via < V; via++){
			for(int s = 0; s < V; s++){
				for(int e = 0; e < V; e++){
					path[s][e] = Math.min(path[s][e], path[s][via] + path[via][e]);
				}
			}
		}
	}

}

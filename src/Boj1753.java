import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1753 {
	private static final String SPACE = " ";
	private static final String INF = "INF";
	private static final String NEW_LINE = "\n";
	
	private static final int LIMIT =  300_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int A = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		int[][] matrix = new int[A+1][A+1];
		
		for(int i = 0; i < A+1; i++){
			for(int j = 0; j < A+1; j++){
				if(i == j){
					matrix[i][j] = 0;
				}
				else{
					matrix[i][j] = LIMIT;
				}
			}
		}
		
		matrix[0][0] = LIMIT;
		
		while(E-- > 0){
			st = new StringTokenizer(br.readLine(), SPACE);
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			
			matrix[start][end] = val;
			
		}
		br.close();
		
		
		for(int v = 1; v < A + 1; v++){
			for(int s = 1; s < A + 1; s++){
				for(int e = 1; e < A+1; e++){
					matrix[s][e] = Math.min(matrix[s][e], matrix[s][v] + matrix[v][e]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < A+1; i++){
			if(matrix[K][i] >= LIMIT){
				sb.append(INF).append(NEW_LINE);
			}
			else{
				sb.append(matrix[K][i]).append(NEW_LINE);
			}
		}		
		System.out.println(sb.toString());
	}

}

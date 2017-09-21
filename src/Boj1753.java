import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1753 {
	private static final String SPACE = " ";
	private static final String INF = "INF";
	private static final String NEW_LINE = "\n";
	
	private static final int LIMIT =  100_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		int[][] matrix = new int[V+1][V+1];
		int[] cost = new int[V+1];
		
		for(int i = 0; i < V+1; i++){
			cost[i] = LIMIT;
			Arrays.fill(matrix[i], LIMIT);
		}
		
		while(E-- > 0){
			st = new StringTokenizer(br.readLine(), SPACE);
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			
		}
		br.close();
		
		
	}

}

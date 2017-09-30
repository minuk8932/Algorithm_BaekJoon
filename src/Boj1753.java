import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 		ArrayList로 구현해야함.
 * 		... 아직 부족하
 */

public class Boj1753 {
	private static final String SPACE = " ";
	private static final String INF = "INF";
	private static final String NEW_LINE = "\n";
	
	private static final int LIMIT =  20_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int A = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		int[][] matrix = new int[A+1][A+1];
		int[] cost = new int[E];
		
		for(int i = 0; i < A+1; i++){
			Arrays.fill(matrix[i], LIMIT);
			cost[i] = LIMIT;
		}
		
		for(int i = 0; i < E; i++){
			st = new StringTokenizer(br.readLine(), SPACE);
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			matrix[u][v] = Math.min(w, matrix[u][v]);	
			
		}
		br.close();
		
		matrix[K][K] = 0;
		cost[K] = 0;		
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		pq.offer(K);
		
		while(!pq.isEmpty()){
			int current = pq.poll();
			
			for(int next = 1; next < A+1; next++){
				if(cost[next] > matrix[current][next] + cost[current]){
					cost[next] = matrix[current][next] + cost[current];
					
					pq.offer(next);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int res = 1; res < E; res++){
			if(cost[res] <= 10){
				sb.append(cost[res]).append(NEW_LINE);
			}
			else{
				sb.append(INF).append(NEW_LINE);
			}
		}
		System.out.println(sb.toString());
	}

}

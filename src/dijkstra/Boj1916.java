package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1916 {
	private static final String SPACE = " ";
	private static final int LIMIT = 100_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		int[][] city = new int[n+1][n+1];
		int[] cost = new int[n+1];
		
		for(int i = 1; i < n+1; i++){
			cost[i] = LIMIT;
			Arrays.fill(city[i], LIMIT);
		}
		
		StringTokenizer st = null;
		
		while(m-- > 0){
			st = new StringTokenizer(br.readLine(), SPACE);
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			
			city[start][end] = Math.min(val, city[start][end]);
		}
		
		st = new StringTokenizer(br.readLine(), SPACE);
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		br.close();
		
		city[s][s] = 0;
		cost[s] = 0;
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		pq.offer(s);
		
		while(!pq.isEmpty()){
			int current = pq.poll();
			
			for(int next = 1; next < n+1; next++){
				if(cost[next] > city[current][next] + cost[current]){
					cost[next] = city[current][next] + cost[current];
					
					pq.offer(next);
				}
			}
		}
		
		System.out.println(cost[e]);
	}

}

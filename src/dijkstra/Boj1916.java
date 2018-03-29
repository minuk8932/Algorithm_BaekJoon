package dijkstra;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1916번 : 최소비용 구하기
 *
 * @see https://www.acmicpc.net/problem/1916/
 *
 */
public class Boj1916 {
	private static final String SPACE = " ";
	private static final int LIMIT = 100_001;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		int[][] city = new int[n+1][n+1];					// 각 도시별 가중치를 담을 배열
		int[] cost = new int[n+1];							// 어느 한 도시에 도달할때 마다의 가중치 최솟값을 저장

		for(int i = 1; i < n+1; i++){								// 초기화
			cost[i] = LIMIT;
			Arrays.fill(city[i], LIMIT);
		}
		
		StringTokenizer st = null;
		
		while(m-- > 0){
			st = new StringTokenizer(br.readLine(), SPACE);
			
			int start = Integer.parseInt(st.nextToken());			// 출발도시
			int end = Integer.parseInt(st.nextToken());				// 도착도시
			int val = Integer.parseInt(st.nextToken());				// 가중치
			
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
			
			for(int next = 1; next < n+1; next++){								// 우선순위 큐를 통한 값 구하기
				if(cost[next] > city[current][next] + cost[current]){	// 다음 도시의 비용 > 현재부터 다음 도시까지 가는 비용 + 현재까지의 최소비용 일때, 
					cost[next] = city[current][next] + cost[current];		// 다음 도시의 비용에 우항 값을 할당
					
					pq.offer(next);														// 도시별로 돌아가면서 구해나감
				}
			}
		}
		
		System.out.println(cost[e]);												// 최종 도착지점에서 최소 비용을 출력
	}

}

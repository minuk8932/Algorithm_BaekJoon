package topology_sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 * 	백준 1516번: 게임 개발
 *
 * 	@see https://www.acmicpc.net/problem/1516/
 *
 */
public class Boj1516 {
	private static int[] res = null;
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		res = new int[N + 1];				// 최종 개발에 필요한 시간
		int[] nodeCost = new int[N + 1];	// 각 건물 별 완성 시간
		int[] indegree = new int[N + 1];	// 상위 노드의 갯수
		ArrayList<Integer>[] map = new ArrayList[N + 1];
		
		for(int i = 0; i < N + 1; i++) {
			map[i] = new ArrayList<>();
			nodeCost[i] = -1;
		}
		
		for(int to = 1; to < N + 1; to++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			nodeCost[to] = Integer.parseInt(st.nextToken());		// 현 건물의 짓는 시간
			
			while(st.hasMoreTokens()) {
				int from = Integer.parseInt(st.nextToken());		// 현 건물보다 먼저 지어져야 하는 건물의 번호
				
				if(from == -1) break;
				
				map[from].add(to);		// 상위 노드의 배열에 현 건물의 번호를 저장
				indegree[to]++;			// 현 건물의 위상 +1
			}
		}
		
		topologySort(N, map, nodeCost, indegree);	// 위상 정렬 메소드
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < N + 1; i++) {		// 1 ~ N 번까지 각각의 건물이 지어지는데 걸리는 시간을 버퍼에 담고
			sb.append(res[i]).append(NEW_LINE);
		}
		
		System.out.println(sb);		// 결과 값 한번에 출력
	}
	
	/**
	 * 위상 정렬 메소드
	 * @param n: 건물 갯수
	 * @param map: 각 건물간 관련 정보
	 * @param cost: 건물을 짓는 비용
	 * @param ind: 상위 노드의 갯수
	 */
	private static void topologySort(int n, ArrayList<Integer>[] map, int[] cost, int[] ind) {
		Queue<Integer> q = new LinkedList<>();
		
		for(int i = 1; i < n + 1; i++) {
			if(ind[i] == 0) {			// 상위 노드가 없는 건물을 먼저 큐에 저장
				q.offer(i);
				res[i] = cost[i];		// 상위 노드가 없으므로 그 건물의 완성 시간은 오로지 해당 건물의 완성 시간
			}
		}
		
		for(int i = 1; i < n + 1; i++) {
			while(!q.isEmpty()) {
				int current = q.poll();
				
				for(int next: map[current]) {
					ind[next]--;
					
					if(res[next] < res[current] + cost[next]) {			// 조건에 맞게 해당 건물을 짓기 전에 상위 건물이 지어졌는지 확인 후, 그만큼의 비용을 계속 더함
						res[next] = res[current] + cost[next];
					}
					
					if(ind[next] == 0) q.offer(next);		// 건물이 완성되었으면 하위 건물을 지어줌
				}
			}
		}
	}
}

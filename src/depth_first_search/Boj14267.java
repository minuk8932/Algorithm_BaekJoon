package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14267번: 내리 갈굼
 *
 *	@see https://www.acmicpc.net/problem/14267/
 *
 */
public class Boj14267 {
	private static final String SPACE = " ";

	private static ArrayList<Integer>[] mem = null;
	private static int[] cost = null;

	private static final int INF = 100_001;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		mem = new ArrayList[n + 1];
		for (int i = 0; i < n + 1; i++) {
			mem[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i < n + 1; i++) {
			int tmp = Integer.parseInt(st.nextToken());

			if(tmp == -1) continue;		// 사장은 갈굼을 받지 않음
			
			mem[tmp].add(i);
		}

		cost = new int[INF];
		
		// 갈굼 누적
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			cost[Integer.parseInt(st.nextToken())] += Integer.parseInt(st.nextToken());
		}
		
		search(1);	// 깊이 우선 탐색을 통한 갈굼 정도 누

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < n + 1; i++) {		// 버퍼에 해당 갈굼을 담아 준 후
			sb.append(cost[i]).append(SPACE);
		}

		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
	
	/**
	 * 갈굼이 시작되는 직원의 번호를 통한 깊이 우선 탐색
	 * 
	 */
	private static void search(int start) {
		for(int next: mem[start]) {
			cost[next] += cost[start];
			
			search(next);
		}
	}
}

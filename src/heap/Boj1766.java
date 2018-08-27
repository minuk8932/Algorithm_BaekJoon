package heap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1766번: 문제집
 *
 *	@see https://www.acmicpc.net/problem/1766/
 *
 */
public class Boj1766 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] visit = new int[N + 1];
		
		ArrayList<Integer>[] map = new ArrayList[N + 1];
		for(int i = 0; i < N + 1; i++) map[i] = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			map[from].add(to);
			visit[to]++;		// 해당 문제보다 먼저 처리될 문제의 갯수 
		}
		
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i = 1; i < N + 1; i++) {
			if(visit[i] == 0) pq.offer(i);		// 자신보다 먼저 처리될 문제가 없는 경우를 먼저 우선순위 큐에 담음
		}
		
		while(!pq.isEmpty()) {
			int current = pq.poll();
			
			sb.append(current).append(SPACE);		// 버퍼에 현재 풀어야 할 문제 저장
			
			for(int next: map[current]) {			// 현재 다음으로 처리될 문제집 번호를 가져옴
				visit[next]--;
				
				if(visit[next] == 0) pq.offer(next);		// 그 중 우선적으로 처리 될 문제를 우선순위 큐에 담음
			}
		}
		
		System.out.println(sb.toString());			// 결과 값 한번에 출력
	}
}

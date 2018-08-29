package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13931번: 숨바꼭질 4
 *
 *	@see https://www.acmicpc.net/problem/13913/
 *
 */
public class Boj13913 {
	private static final int[] MOVES = {1, -1, 2};
	private static final int INF = 200_001;
	
	private static StringBuilder sb = new StringBuilder();
	private static int res = 0;
	private static int[] isVisited = new int[INF];
	
	private static final char SPACE = ' ';
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Arrays.fill(isVisited, -1);		// 방문 배열 초기화
		
		// N == K 이면 res = 0, 그외 bfs 메소드의 결과 -> 이때 res == 0 인지 아닌지에 따라 버퍼에 담는 값을 구분
		sb.append((res = N == K ? 0 : bfs(N, K)) == 0 ? res + NEW_LINE + N : res + NEW_LINE);
		
		if(N != K) {						// 이동해서 목적지를 찾는 경우
			int[] result = new int[INF];
			int step = K;					// 도착 지점부터 역방향으로 찾아나감
			
			for (int i = res - 1; i >= 0; i--) {
                result[i] = isVisited[step];		// 결과 배열에 해당 위치를 저장 후
                step = isVisited[step];			// 위치 갱신
            }
			
            for (int i = 0; i < res; i++) sb.append(result[i]).append(SPACE);		// 버퍼에 결과 배열의 값을 순서대로 담고
            sb.append(K);	// 목적지도 함께 담아줌
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
	
	/**
	 * 너비 우선 탐색 메서드
	 * 
	 */
	private static int bfs(int start, int end) {
		int[] cnt = new int[INF];
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		
		isVisited[start] = start;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(final int MOVE: MOVES) {
				int next = MOVE == 2 ? current * MOVE : current + MOVE;
				
				if(next >= 0 && next < INF) {
					if(isVisited[next] == -1) {
						isVisited[next] = current;			// 이전 위치의 값을 현재 배열에 저장
						cnt[next] = cnt[current] + 1;		// 목적지까지 가는데 걸리는 횟수 저장
							
						if(next == end) return cnt[next];
							
						q.offer(next);
					}
				}
			}
		}
		return 0;
	}
}

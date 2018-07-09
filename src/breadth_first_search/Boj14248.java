package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14248번: 점프 점프
 *
 *	@see https://www.acmicpc.net/problem/14248/
 *
 */
public class Boj14248 {
	private static int[] map = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		map = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N + 1; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = Integer.parseInt(br.readLine());	// 시작점
		
		System.out.println(bfs(start, N));		// 너비 우선 탐색을 통해 방문 가능 점의 수를 구함
	}
	
	/**
	 * 너비 우선 탐색 알고리즘
	 * @param s	시작
	 * @param N	전체 크기
	 * @return	방문 가능한 점의 개수
	 */
	private static int bfs(int s, int N) {
		int cnt = 0;
		
		boolean[] isVisited = new boolean[N + 1];
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(s);
		
		isVisited[s] = true;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			int[] directions = {1 * map[current], -1 * map[current]};	// 이동가능한 점을 방향 배열에 담아줌
			
			for(int next : directions) {		// 다음 이동 할 정점의 인덱스를 구함
				next += current;
				
				if(next > 0 && next < N + 1) {
					if(!isVisited[next]) {
						isVisited[next] = true;		// 방문가능한 점인 경우 참으로 변경
						
						q.offer(next);
					}
				}
			}
		}
		
		for(int i = 1; i < N + 1; i++) {
			if(isVisited[i]) cnt++;		// 방문가능 점의 갯수를 구한 후
		}
		
		return cnt;		// 해당 값 반환
	}
}

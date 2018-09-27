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
 *	백준 12851번: 숨바꼭질 2
 *
 *	@see https://www.acmicpc.net/problem/12851/
 *
 */
public class Boj12851 {
	private static final int[] DIRECTIONS = {-1, 1, 2};
	private static final int SIZE = 100_001;
	private static final int INF = 10_000_001;
	
	private static int[] isVisited = new int[SIZE];
	private static int counts = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Arrays.fill(isVisited, INF);
		bfs(N, K);						// 너비 우선 탐색 메소드를 통한 최단 거리
		int resCnt = isVisited[K] - 1;
		
		bfsCnt(N, K, resCnt);			// 너비 우선 탐색 메소드를 통한 최단 거리 갯수 구하기
		
		System.out.println(N == K ? 0 + "\n" + 1 : resCnt + "\n" + counts);	// N == K인 경우 외 다른 경우는 메소드를 통한 결과 값으로 출력
	}
	
	/**
	 * 너비 우선 탐색 메소드(최단거리)
	 * 
	 */
	private static void bfs(int start, int end) {		
		Queue<Integer> q = new LinkedList<>();
		
		q.offer(start);
		isVisited[start] = 1;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			int next = 0;
			
			for(final int D: DIRECTIONS) {
				if(D == 2) next = current * D;
				else next = current + D;
				
				if(next >= 0 && next < SIZE) {
					if(isVisited[next] > isVisited[current] + 1) {		// 보다 작은 값이 들어오면 next 배열에 저장
						isVisited[next] = isVisited[current] + 1;
						
						q.offer(next);
					}
				}
			}
		}
	}
	
	/**
	 * 최단 경로의 갯수를 구하는 메소드
	 * 
	 */
	private static void bfsCnt(int start, int end, int finish) {
		boolean[] chk = new boolean[SIZE];
		int cnt = 0;
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		
		while(!q.isEmpty()) {
			cnt++;
			int qSize = q.size();
			
			while(qSize-- > 0) {			// 해당 큐의 사이즈 만큼
				int current = q.poll();
				
				chk[current] = true;
				int next = 0;
				
				for(final int D: DIRECTIONS) {		// 큐 내에 인접한 모든것을 연산하여 큐에 저장
					if(D == 2) next = current * D;
					else next = current + D;
					
					if(next >= 0 && next < SIZE) {
						if(!chk[next]) {
							if(next == end && cnt == finish) counts++;	// 목적지 도달시, 같은 거리만큼 이동했다면 counts +1
							
							q.offer(next);
						}
					}
				}
			}
		}
	}
}

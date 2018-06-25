package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1697 : 숨바꼭질
 *
 *	@see https://www.acmicpc.net/problem/1697
 *
 */
public class Boj1697 {
	private static final String SPACE = " ";
	
	private static final int[] DIRECTIONS = {-1, 1, 2};
	private static final int MAX = 100_001;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		br.close();
		
		int res = Integer.MAX_VALUE;
		
		int[] isVisited = new int[MAX];
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(N);
		isVisited[N] = 1;														// 시작점 방문 체크
		
LOOP:	while(!q.isEmpty()){
			int current = q.poll();
			
			for(final int DIRECTION : DIRECTIONS){
				int next = 0;
				if(DIRECTION == 2){
					next = current * DIRECTION;						// 이동 가능 경로가 2가 들어오면 곱해주고
				}
				else{
					next = current + DIRECTION;						// 이외에는 합이나 차
				}
				
				if(next >= 0 && next < MAX){
					if(isVisited[next] == 0){
						isVisited[next] = isVisited[current] + 1;	// 올바른 경로로 접근시 이전 방문 배열값의 +1
						
						if(next == K){											// 목적지에 도착한 경우
							if(res > isVisited[next]){
								res = isVisited[next];						// res에 해당 방문 배열 값을 담고
								break LOOP;									// 루프 종료
							}
						}
						q.offer(next);
					}
				}
			}
		}
		
		System.out.println(N == K ? 0 : res - 1);					// N == K인 경우 같은 위치에 있으므로 0, 이외에는 res값에서 처음 방문값을 뺀 나머지를 출력
	}
}

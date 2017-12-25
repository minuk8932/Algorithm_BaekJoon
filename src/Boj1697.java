import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*	*
 * 이동가능한 방법
 * 		1. 걷기 : 현재 +1 / -1
 * 		2. 순간이동 :  현재 x2
 * 	-> 즉 이동 가능한 direction : 3 가지
 * 
 * 		DP 문제이기 떄문에 채점시 시간초과가 나므로 DP 배우고 다시 하기로
 * 		시간초과 뜸, 알고리즘은 잘 짜여져있음
 */

public class Boj1697 {
	public static final String SPACE = " ";
	private static boolean[] isVisited = new boolean[100_001];
		// 방문을 했는지 안했는지 체크 할 배열 -> 이를 통해 이미 지나왔던 위치를 막을지 안막을지 결정 할 것
	private static int K;
	private static int minSec = Integer.MAX_VALUE;
		// 21억으로 잡아둔 수
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		br.close();
		
		dfs(N, 0);
		// 수빈이의 최초 위치 및 시작시간 입력
		
		System.out.println(minSec);
		// 최종 최단시간 출력
	}
	
	private static void dfs(int current, int sec){ 	//현재 위치(current)를 통해 계산 할 함수 생성, 현재 시간 0초를 입력 받을 매개 변수도 포함
		System.out.println(current+" "+sec);
		if(!isVisited[current] && sec < minSec){	// 수빈이가 처음 5에서 시작하는데 5라는 위치를 방문한 적이 있는가? 없으면 밑의 내용들을 실행 하겠음
													// minSec를 통한 무한 루프 방지
			
			isVisited[current] = true; // 이제 들어왔으니 5라는 위치를 왔었으니 true로 바꾸어 위에 조건문에서 실행되지 못하게 막는다
			
			if(current == K){			// 목적지 K와 수빈이의 위치 N이 동일해 졌으면 종료
				minSec = sec;
			}
			
			int next = current - 1;
			
			if(next >= 0){		// 뒤로 갔을 경우에 0이하로 가지 않아 문제의 조건(0~100_000)을 벗어나지 않는 경우인지
								// 만약 벗어난다면 오류이므로 함수를 실행하지 못하도록 막는다.
				dfs(next, sec+1);
			}
			
			next = current + 1;
			
			if(next <= 100_000){
				dfs(next, sec+1);
			}
			
			next = 2 * current;
			
			if(next <= 100_000){
				dfs(next, sec+1);
			}
			
			isVisited[current] = false;
				// 원래 방문점을 false로 풀어 줌
				// why? 탐색 된 이후엔 다시 풀어줘야 만약에 빠꾸 했을때도 다시 지나갈 수 있음
		}
		
	}
}

package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11060번: 점프 점프
 *
 *	@see https://www.acmicpc.net/problem/11060/
 *
 */
public class Boj11060 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {					// 점프 발판의 값을 저장
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N];
		boolean[] visit = new boolean[N];
		
		dp[0] = 0;
		
		for(int i = 0; i < nums[0] + 1; i++) {		// 초기 방문 할 수 있는 칸을 구함
			if(i > N - 1) break;
			
			visit[i] = true;
		}
		
		for(int i = 0; i < N; i++) {
			if(!visit[i]) break;			// 만약 반복문을 도는 동안 방문하지 못하는 칸이라면 종료
			
			for(int j = i + 1; j < nums[i] + i + 1; j++) {	// 현재 칸부터 점프해서 갈 수 있는 칸까지 모두 탐색
				if(j > N - 1) break;				// 점프시 목적지를 넘어가는 경우 반복문 종료
				
				if(dp[j] == 0) {					// 만약 처음 도달한 칸이라면 출발한 칸 +1
					dp[j] += (dp[i] + 1);
				}
				else {										// 이미 한번 방문 했던 칸이면, 최소값으로 갱신
					dp[j] = Math.min(dp[i] + 1, dp[j]);
				}
				
				visit[j] = true;							// 방문 하고 있는 칸을 도달 할 수 있음으로 갱신
			}
		}
		
		System.out.println(visit[N - 1] ? dp[N - 1] : -1);	// 최종칸이 방문 불가능 하다면 -1, 그 외 도달하기까지 걸리는 값
	}
}

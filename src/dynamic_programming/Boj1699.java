package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author minchoba
 *	백준 1699번 : 제곱수의 합
 *
 *	@see http://www.acmicpc.net/problem/1699
 *
 */

public class Boj1699 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];
		
		br.close();
		
		for(int i = 1; i < N + 1; i++){
			dp[i] = i;
		}
		
		
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j * j < i + 1; j++) {	// LIS와 유사하게 풀이 가능, 제곱수의 합 이므로 해당 제곱수까지의 경우를 연산하는 반복문
                if (dp[i] > dp[i - j * j] + 1) {		//	현재 dp 배열에서 제곱수의 합 개수와, 이전 제곱수의 합에 1을 더한 것 중
                    dp[i] = dp[i - j * j] + 1;			//	더 큰 값을 dp배열에 할당
                }
            }
        }
		
		System.out.println(dp[N]);				// 해당 값에서의 제곱수의 합 출력
	}
}

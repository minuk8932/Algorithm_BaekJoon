package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2133번 : 타일 채우기
 *
 *	@see https://www.acmicpc.net/problem/2133
 *
 */

public class Boj2133 {
	private static final int MAX = 30;
	
	/**
	 * 
	 * @param args
	 * @throws Exception
	 * 
	 *  i) 3 x N 타일링으로 홀 수일 경우는 존재하지 않는다.
	 *  ii) 규칙적으로 나오는 타일 모양이 있는 반면, 각 경우당 오직 2개씩 예외적인 타일 모양이 생성 됨 (즉, 경우마다 2개씩 계속 더해준다)
	 *  
	 */
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		br.close();
		
		if(N % 2 == 1){
			System.out.println(0);
			return;
		}
		else{
			int[] dp = new int[MAX + 1];
			dp[0] = 1;
			dp[2] = 3;
			
			for (int i = 4; i < N + 1; i += 2){
		        dp[i] = dp[i - 2] * 3;			// 규칙적인 타일
		        
		        for (int j = 4; j < i + 1; j += 2){
		            dp[i] += dp[i - j] * 2;		// 불규칙적인 타일 ... ii)
		        }
		    }
			System.out.println(dp[N]);	// 결과 출력
		}
	}
}

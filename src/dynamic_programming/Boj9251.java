package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * @author minchoba
 *		Boj 9251번 : LCS 문제
 *					최장 공통 부분 알고리즘 (Longest Common Subsequence)
 *
 */
public class Boj9251 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String stand = br.readLine();
		String comp = br.readLine();

		int leng2 = comp.length();
		int leng1 = stand.length();
		int[][] dp = new int[leng2 + 1][leng1 + 1];
		
		// dp를 통한 최장 공통부분 수치 계산 :  1열의 경우
		for(int i = 1; i < leng1 + 1; i++){
			if(comp.charAt(0) == stand.charAt(i - 1)){			// 해당 비교 단어가 서로 같다면 좌측 상단의 dp값 + 1
				dp[1][i] = dp[0][i - 1] + 1;
			}
			else{
				dp[1][i] = Math.max(dp[0][i], dp[1][i-1]);				// 같지 않다면, 바로 왼쪽의 dp 값과 바로 위의 dp값의 최대 값을 현재 배열에 할당
			}
		}
		
		// 배열 전체에 값 할당
		for(int i = 2; i < leng2 + 1; i++){
			for(int j = 1; j < leng1 + 1; j++){
				if(comp.charAt(i - 1) == stand.charAt(j - 1)){
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
				else{
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j-1]);
				}
			}
		}
		
		int max = 0, cnt = 0;
		
		// 최장 공통부분의 길이 측정
		for(int i = 1; i < leng2 + 1; i++){
			for(int j = 1; j < leng1 + 1; j++){
				if(max < dp[i][j] && dp[i][j] > dp[i][j - 1]){
					max = Math.max(max, dp[i][j]);
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}

package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 9252번 : LCS2
 *
 *	@see https://www.acmicpc.net/problem/9252
 *
 */

public class Boj9252 {
	private static int[][] dp = null;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String stand = br.readLine();
		String comp = br.readLine();
		br.close();

		int lengS = stand.length();
		int lengC = comp.length();

		dp = new int[lengC + 1][lengS + 1];

		for (int i = 1; i < lengS + 1; i++) { 							// dp = 1
			if (comp.charAt(0) == stand.charAt(i - 1)) {
				dp[1][i] = dp[0][i - 1] + 1;
			} 
			else {
				dp[1][i] = Math.max(dp[1][i - 1], dp[0][i]);
			}
		}

		for (int i = 2; i < lengC + 1; i++) { 							// dp = 2 ~ n
			for (int j = 1; j < lengS + 1; j++) {
				if (comp.charAt(i - 1) == stand.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} 
				else {
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
				}
			}
		}
		
		System.out.println(dp[lengC][lengS]);					// 전체 길이 출력 : LCS(9251) 문제와 같은 방식 
		
		String res = "0";
		
		for (int i = lengC; 0 < i; i--) {								// 문자열 추적
			for (int j = lengS; 0 < j; j--) {

				if (dp[i][j] != Math.max(dp[i - 1][j], dp[i][j - 1])) {	// 최장 공통 부분 문자열의 앞과 이전 짧은 다어와의 비교 길이가 현재 길이와 다르다면
					res += stand.charAt(j-1);								// 공통부분의 추가
					lengS = j - 1;
					
					break;
				}

				if (dp[i][j] == dp[i][j - 1] && dp[i][j] != dp[i - 1][j]){	// 만약 이전 길이와 같고, 이전 짧은 단어와의 비교 길이가 다르다면
					continue; 															// 다음 단어로 넘어감
				}
				
				if (dp[i][j] != dp[i][j - 1] && dp[i][j] == dp[i - 1][j]){	// 만약 이전 길이와 다르고, 이전 짧은 단어와의 비교 길이가 같다면
					break;																// 주어진 단어의 다음 인덱스로 넘어감
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int resL = res.length();													// 결과 단어의 길이
		
		for(int i = resL; i > 1; i--){												// 결과 단어를 역 추적 : 배열에서 뒤에서부터 탐색했으므로
			if(res.charAt(i - 1) != '0'){											// 초기값 제외
				sb.append(res.charAt(i - 1));
			}
		}
		
		System.out.println(sb.toString());									// 결과 단어 출력
	}
}

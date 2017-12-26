package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 *	백준 11055번 : 가장 큰 증가하는 부분 수열
 *
 *	@see https://www.acmicpc.net/problem/11055
 *
 */

public class Boj11055 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		//버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		for(int i = 1; i < N + 1; i++){
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N + 1];
		
		dp[1] = A[1];
		
		for(int i = 1; i < N + 1; i++){
			for(int j = 1; j < i + 1; j++){
				if(A[i] > A[j - 1]){
					dp[i] = Math.max(dp[i], dp[j - 1] + A[i]);		/** 
																					* 	@see Boj11053 : 해당 문제와 풀이는 유사함
																					* 		차이점은 위의 문제는 증가 할 때 마다 1씩 더해서 dp 배열에 저장했지만,
																					* 		이 문제의 핵심은 해당 인덱스 안의 값을 합하여 그 최대 값을 찾는 것.
																					*/
				}
			}
		}
		
		int max = 0;
		for(int i = 1; i < N + 1; i++){
			max = Math.max(dp[i], max);							// 각 자리별로 부분 수열의 합 중 가장 큰 값을 max에 저장
		}
		
		System.out.println(max);									// 결과 출력
	}
}

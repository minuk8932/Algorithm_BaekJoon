package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2352번: 반도체
 *
 *	@see https://www.acmicpc.net/problem/2352/
 *
 */
public class Boj2352 {
	private static final String SPACE = " ";
	private  static final int INF = 40_001;
	
	private static int[] dp = null;
	private static int[] arr = null;
	private static int n = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		 arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		for(int i = 0; i < arr.length; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[INF];		// 증가 수열의 길이
		dp[0] = 1;
		
		for(int i = 1; i < n; i++){
			dp[i] = 1;							// 비교할 숫자: 기본 1개
			
			for(int j = 0; j < i; j++){
				if(arr[i] > arr[j] && dp[i] < dp[j] + 1){		// 현재 값이 0 ~ (i - 1)까지의 값들 보다 클 경우, 동시에 최장 부분 증가 수열의 길이가 현재 까지의 경우보다 짧을 때 
					dp[i] = dp[j] + 1;					// 길이 갱신
				}
			}
		}
		
		
		System.out.println(maxLength());		// 최대 길이 반환 메소드를 통한 결과 출력
	}
	
	/**
	 * 최대 길이 반환 메소드
	 * 	@return: 길이 최댓값
	 */
	private static int maxLength(){
		int max = 0;
		
		for(int i = 0; i < n; i++){
			if(dp[i] > max){
				max = dp[i];			// 배열 내의 가장 큰 값을 받고
			}
		}
		
		return max;		// 해당 값 반환
	}
}

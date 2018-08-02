package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14929번: 귀찮아(SIB)
 *
 *	@see https://www.acmicpc.net/problem/14929/
 *
 */
public class Boj14929 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] nums = new int[N + 1];
		int[] setSums = new int[N + 1];
		int total = 0;
		
		/**
		 * 각 원소들을 정렬해서 적어보면 
		 * 
		 * 		∑ (X_i * ∑ X_k)	 (단, i: 1 ~ n, k: i+1 ~ n-1) 이러한 공식이 도출됨
		 *
		 */
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N + 1; i++) {					// nums에는 각 원소들을,
			nums[i] = Integer.parseInt(st.nextToken());		// setSums에는 각 1부터 i까지의 부분합 저장
			setSums[i] = nums[i] + setSums[i - 1];
		}
		
		total = setSums[N];				// N까지의 총 부분합을 총계에 저장 후
		long res = 0;
		
		for(int i = 1; i < N + 1; i++) {
			res += nums[i] * (total - setSums[i]);	// 결과값을 계산 (total - 부분합): ∑ X_k	 (단, k: i+1 ~ n-1)을 구하는 과정
		}
		
		System.out.println(res);		// 결과값 출력
	}
}
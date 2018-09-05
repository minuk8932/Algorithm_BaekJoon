package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2559번: 수열
 *
 *	@see https://www.acmicpc.net/problem/2559/
 *
 */
public class Boj2559 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int sum = 0;
		int[] nums = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			if(i < K) sum += nums[i];				// 우선적으로 앞의 K개 만큼의 합을 구함
		}
		
		int[] res = new int[N - K + 1];
		res[0] = sum;
		
		for(int i = 1; i + K < N + 1; i++) {		// i - 1 번째 부터 값을 빼고, 그 뒤의 다음 번째 값을 더해서 i번째 총 합을 구해 배열에 저장
			res[i] = res[i - 1] - nums[i - 1] + nums[i + K - 1];
		}
		
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < res.length; i++) {		// 결과 배열 중 최댓 값을 탐색
			if(res[i] > max) max = res[i];
		}
		
		System.out.println(max);		// 결과 값 출력
	}
}

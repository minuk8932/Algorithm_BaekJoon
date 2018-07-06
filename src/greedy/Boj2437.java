package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2437번: 저울
 *
 *	@see https://www.acmicpc.net/problem/2437/
 *
 */
public class Boj2437 {	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] nums = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());	
		}
		
		Arrays.sort(nums);
		int sum = 0;
		
		for(int i = 0; i < N; i++) {
			if(sum + 1 < nums[i]) break;	// 현 인덱스 이전 까지의 합 +1 < 현재 인덱스의 값 이면, 구하지 못하는 값이므로 종료
			sum += nums[i];			// 구할 수 있는 값인 경우 합을 계속해서 저장
		}
				
		System.out.println(++sum);	// 구하지 못하는 최소값 출력
	}
}

package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10773번: 제로
 *
 *	@see https://www.acmicpc.net/problem/10773/
 *
 */
public class Boj10773 {
	private static final int INF = 100_001;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		int[] nums = new int[INF];
		int idx = 0;
		
		for(int i = 0; i < K; i++) {
			int val = Integer.parseInt(br.readLine());
			
			if(val == 0) {		// 0이면 배열의 값을 지우고 인덱스를 앞으로 땡김
				idx--;
				nums[idx] = 0;
			}
			else {				// 정수면 배열에 값을 담고 인덱스를 뒤로 밀어줌
				nums[idx] = val;
				idx++;
			}
		}
		
		int sum = 0;
		
		for(int i = 0; i < INF; i++) {			// 합 연산
			if(nums[i] != 0) sum += nums[i];
		}
		
		System.out.println(sum);		// 결과 값 출력
	}
}

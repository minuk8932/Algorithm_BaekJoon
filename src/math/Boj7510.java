package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 7510번: 고급수학
 *
 *	@see https://www.acmicpc.net/problem/7510/
 *
 */
public class Boj7510 {
	private static final String CASE = "Scenario #";
	private static final String NEW_LINE = "\n";
	private static final String COLON = ":";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 1; i < N + 1; i++) {
			int[] nums = new int[3];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			nums[0] = Integer.parseInt(st.nextToken());
			nums[1] = Integer.parseInt(st.nextToken());
			nums[2] = Integer.parseInt(st.nextToken());
			
			int max = 0, sum = 0;
			
			max = Math.max(nums[0], Math.max(nums[1], nums[2]));	// 최댓값 구하기
			max *= max;		// 제곱수
			
			for(int j = 0; j < nums.length; j++) {
				nums[j] *= nums[j];
				
				if(max != nums[j]) {	// 최댓값 제외 나머지의 제곱수 합
					sum += nums[j];
				}
			}
			
			// max == sum이면 yes 아니면 no 버퍼에 저장
			sb.append(CASE).append(i).append(COLON).append(NEW_LINE).append(sum == max ? "yes" : "no").append(NEW_LINE).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());	// 결과값 한번에 출력
	}
}

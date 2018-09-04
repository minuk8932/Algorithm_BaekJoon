package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9076번: 점수 집계
 *
 *	@see https://www.acmicpc.net/problem/9076/
 *
 */
public class Boj9076 {
	private static final String ERROR = "KIN";
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int[] nums = new int[5];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < nums.length; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(nums);				// 점수 집계
			
			if(nums[3] - nums[1] >= 4) {			// 점수 조정 필요
				sb.append(ERROR).append(NEW_LINE);
			}
			else {					// 그 외
				int sum = 0;
				
				for(int i = 1; i < 4; i++) {
					sum += nums[i];
				}
				
				sb.append(sum).append(NEW_LINE);
			}
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
}

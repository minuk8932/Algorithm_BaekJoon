package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9613번: GCD 합
 *
 *	@see https://www.acmicpc.net/problem/9613/
 *
 */
public class Boj9613 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		
		while(t-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			
			int[] nums = new int[n];
			for(int i = 0; i < n; i++) nums[i] = Integer.parseInt(st.nextToken());
			
			long res = 0;
			
			for(int i = 0; i < n; i++) {				// 중복없이 차례로 GCD 합을 getGcd 메소를 통해 구함
				for(int j = i + 1; j < n; j++) {
					res += getGcd(nums[i], nums[j]);
				}
			}
			
			sb.append(res).append(NEW_LINE);		// 각각의 총 합을 버퍼에 담은 후
		}
		
		System.out.println(sb.toString());			// 결과 값 한번에 출력
	}
	
	/**
	 * 최대 공약수 메소드
	 * 
	 */
	private static int getGcd(int num1, int num2) {
		if(num2 == 0) return num1;
		
		return getGcd(num2, num1 % num2);
	}
}

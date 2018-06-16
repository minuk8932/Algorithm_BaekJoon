package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2145번: 숫자놀이
 *
 *	@see https://www.acmicpc.net/problem/2145/
 *
 */
public class Boj2145 {
	private static final String STOP = "0";
	private static final String NEW_LINE = "\n";
	private static final int MAKE_UNI = 10;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String num = br.readLine();
			if(num.equals(STOP)) break;		// 입력으로 0이 들어오는 경우 반복문 종료
			
			char[] nums = num.toCharArray();
			int res = (nums[0] - 48);				// 배열의 첫번째 값을 받고
			
			for(int i = 1; i < nums.length; i++) {
				res += (nums[i] - 48);					// 그 다음 값을 결과 변수에 더한 후
				
				if(res > 9) {									// 결과 변수가 10이상이 되면 10의 자리와 1의 자리수를 더해줌
					res = (res % MAKE_UNI) + (res / MAKE_UNI);
				}
			}
			
			sb.append(res).append(NEW_LINE);		// 최종 결과를 버퍼에 담고
		}
		
		System.out.println(sb.toString());			// 결과값 한번에 출력
	}
}

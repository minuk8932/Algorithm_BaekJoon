package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2231번: 분해 합
 *
 *	@see https://www.acmicpc.net/problem/2231/
 *
 */
public class Boj2231 {
	private static final char MAKER = '9';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int N = Integer.parseInt(input);
		
		int leng = input.length();
		String nine = "";
		
		for(int i = 0; i < leng - 1; i++) {
			nine += MAKER;
		}
		
		int tmp = N - Integer.parseInt(nine); // 최소 시작 값을 구함
		int start = tmp < 0 ? 0 : tmp;			// 0보다 작으면 0을 입력
		
		int res = 0;
		for(int i = start; i < N + 1; i++) {	// 각 숫자를 문자열로 받아온 후
			String nums = String.valueOf(i);
			int sum = 0;
			
			for(char n: nums.toCharArray()) {	// 한자리마다 더해주고
				sum += n - (MAKER - 9);
			}
			
			sum += i;					// 최종 해당 값을 더한 후
			if(sum == N) {				// 그 값이 N과 같으면
				res = i;			// 저장 후 종료
				break;
			}
		}
		
		System.out.println(res);		// 결과 값 출력
	}
}

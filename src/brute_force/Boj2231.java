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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int N = Integer.parseInt(input);
		
		int leng = input.length();
		String nine = "";
		
		for(int i = 0; i < leng; i++) {
			nine += MAKER;
		}
		
		System.out.println(resolve(N, nine));
	}
	
	private static int resolve(int n, String str) {
		if(str.equals("")) return 0;
		
		int tmp = n - Integer.parseInt(str); 		// 최소 시작 값
		int start = tmp < 0 ? 0 : tmp;				// 0보다 작으면 0을 입력
		
		int res = 0;
		for(int i = start; i < n + 1; i++) {
			String nums = String.valueOf(i);
			int sum = 0;
			
			for(char c: nums.toCharArray()) {
				sum += c - (MAKER - 9);
			}
			
			sum += i;						// 최종 해당 값 더하기
			
			if(sum == n) {
				res = i;
				break;
			}
		}
		
		return res;
	}
}

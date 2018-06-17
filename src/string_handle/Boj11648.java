package string_handle;
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * 
 * 	@author minchoba
 *	백준 11648번: 지속
 *
 *	@see https://www.acmicpc.net/problem/11648/
 *
 */
public class Boj11648 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		char[] num = line.toCharArray();
		
		int keep = 0;
		int val = Integer.parseInt(line);
		
		while(val >= 10) {				// 값이 1의자리가 되면 반복문 종료
			int tmp = 1;
			
			for(int i = 0; i < num.length; i++) {
				tmp *= (num[i] - 48);			// 각 자리를 곱한 값을 tmp 변수에 담음
			}
			
			val = tmp;							// 다시 값을 할당하고
			num = String.valueOf(val).toCharArray();	// val을 문자배열로 바꾸어 num 배열에 다시 할당
			
			keep++;
		}
		
		System.out.println(keep);			// 지속된 횟수를 출력
	}
}

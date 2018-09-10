package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2028번: 자기 복제 수
 *
 *	@see https://www.acmicpc.net/problem/2028/
 *
 */
public class Boj2028 {
	private static final String OK = "YES";
	private static final String NOT_OK = "NO";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			String num = br.readLine();
			int N = Integer.parseInt(num);
			
			int powN = N * N;				// 제곱수
			int leng = num.length();		// 본래 숫자의 길이
			
			if(N == powN % (Math.pow(10, leng))) {	// 원래 수와 제곱수의 맨 뒷자리 수가 같을 때
				sb.append(OK).append(NEW_LINE);		// YES
			}
			else {
				sb.append(NOT_OK).append(NEW_LINE);	// 그 외, NO
			}
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
}

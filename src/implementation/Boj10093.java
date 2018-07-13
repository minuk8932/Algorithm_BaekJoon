package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10093번: 숫자
 *
 *	@see https://www.acmicpc.net/problem/10093/
 *
 */
public class Boj10093 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long num1 = Long.parseLong(st.nextToken());
		long num2 = Long.parseLong(st.nextToken());
		
		long to = Math.max(num1, num2);
		long from = Math.min(num1, num2) + 1;		// 시작과 끝 숫자를 구함
		int res = 0;
		
		StringBuilder sb = new StringBuilder();
		while(to > from) {						// 사이의 숫자를 버퍼에 담고, 갯수를 세어줌
			sb.append(from++).append(SPACE);
			res++;
		}
		
		System.out.println(res + NEW_LINE + sb.toString());		// 결과 값 한번에 출력
	}
}

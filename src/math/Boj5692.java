package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 5692번: 팩토리얼 진법
 *
 *	@see https://www.acmicpc.net/problem/5692/
 *
 */
public class Boj5692 {
	private static final String TERMINATE = "0";
	private static final String NEW_LINE = "\n";
	
	private static final int[] FACTORIAL = {1, 2, 6, 24, 120};
	
	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String num = br.readLine();
			if (TERMINATE.equals(num)) break;	// 0이 입력으로 들어오면 반복문 종료
			
			int res = 0;
			int leng = num.length() - 1;
			
			for(char tmp : num.toCharArray()) {				// 문자를 하나씩 받아오면서 정수형으로 바꿔주고, 해당 순번에 맞는 팩토리얼 값을 곱해 더함
				res += ((tmp - '0') * FACTORIAL[leng--]);
			}
			
			sb.append(res).append(NEW_LINE);		// 합한 값을 버퍼에 담고
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}

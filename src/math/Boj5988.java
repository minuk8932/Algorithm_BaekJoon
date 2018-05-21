package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 5988번: 홀수일까 짝수일까
 *
 *	@see https://www.acmicpc.net/problem/5988/
 *
 */
public class Boj5988 {
	private static final String E = "even";
	private static final String O = "odd";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(N-- > 0){
			String num = br.readLine();
			
			// 매우 큰 수의 데이터가 들어오므로 끝자리로만 짝 홀을 판별해 결과값을 버퍼에 담는다
			sb.append(Character.getNumericValue(num.charAt(num.length() - 1)) % 2 == 0 ? E : O).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());	// 결과값 한번에 출력
	}
}

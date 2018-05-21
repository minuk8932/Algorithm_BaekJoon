package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10869번: 사칙연산
 *
 *	@see https://www.acmicpc.net/problem/10869/
 *
 */
public class Boj10869 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		sb.append(A + B).append(NEW_LINE)		// 더하기
		.append(A - B).append(NEW_LINE)			// 빼기
		.append(A * B).append(NEW_LINE)			// 곱하기
		.append(A / B).append(NEW_LINE)			// 나누기
		.append(A % B).append(NEW_LINE);			// 나머지
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}

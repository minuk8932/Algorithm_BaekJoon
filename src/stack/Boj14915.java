package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14915번: 진법 변환기
 *
 *	@see https://www.acmicpc.net/problem/14915/
 *
 */
public class Boj14915 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		Stack<Character> lifo = new Stack<>();
		
		boolean isZero = m == 0 ? true : false;
		
		while(m > 0) {
			int elem = m % n;
																				// 9를 초과하는 경우 알파벳으로 변경하고
			lifo.push((char) (elem > 9 ? ('A' + (elem - 10)) : elem + '0'));		// 스택에 해당 진수의 뒷자리부터 순서대로 넣어줌
			m /= n;
		}
		
		StringBuilder sb = new StringBuilder();
		while(!lifo.isEmpty()) sb.append(lifo.pop());		// 스택의 값을 꺼내와서 원래 순서로 버퍼에 담음
		
		System.out.println(isZero ? 0 :sb.toString());		// 초기 입력이 0이었다면 0을 그 외, 해당 진법에 따라 출력
	}
}

package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11005번: 진법 변환 2
 *
 *	@see https://www.acmicpc.net/problem/11005/
 *
 */
public class Boj11005 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		char[] alpha = new char[26];
		for(int i = 0; i < alpha.length; i++) {		// 값에 해당하는 알파벳을 뽑아줄 배열
			alpha[i] = (char) ('A' + i);
		}
		
		Stack<Character> lifo = new Stack<>();
		while(N > 0) {
			char tmp = (char) ((N % B) + '0');	// 해당 자리의 진법을 tmp에 저장
			
			if(tmp > '9') {						// 만약 tmp가 '9'보다 큰 경우 -> 알파벳으로 변환
				tmp = alpha[(tmp - '0') - 10];
			}
			
			lifo.push(tmp);			// 스택에 해당 자리의 값을 담고
			N /= B;					// N을 나누어줌
		}
		
		String res = "";
		while(!lifo.isEmpty()) res += lifo.pop();		// 스택의 값들을 문자열에 저장(역순으로, 진법을 형성)
		
		System.out.println(res);			// 결과 값 출력
	}
}

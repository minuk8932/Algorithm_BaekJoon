package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 
 * 	@author minchoba
 *	백준 15926번: 현욱은 괄호왕이야!!
 *
 *	@see https://www.acmicpc.net/problem/15926/
 *
 */
public class Boj15926 {
	private static final char OPEN = '(';
	private static final int INF = 200_001;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		char[] parenthesis = br.readLine().toCharArray();
		Stack<Integer> lifo = new Stack<>();

		boolean[] cutter = new boolean[INF];

		for (int i = 0; i < parenthesis.length; i++) {
			if(parenthesis[i] == OPEN) {			// 열린 괄호의 경우 스택에 바로 담음
				lifo.push(i);
			}
			else {									// 현 괄호가 닫힌 괄호의 경우 스택이 빈 상태가 아니라면, 열린 괄호만 들어 있기 때문에, 
				if(!lifo.isEmpty()) cutter[i] = cutter[lifo.pop()] = true;	// 괄호완성 pop을 하며, 해당하는 인덱스의 값을 참으로 변경
			}
		}
		
		int leng = 0;
		int maxLeng = 0;
		
		for(int i = 0; i < n; i++) {
			if(cutter[i]) {				// 구간 배열이 참인 경우 길이 +1
				leng++;
			}
			else {						// 구간 배열이 거짓인 경우, 최댓값 갱신 후 길이: 0으로 초기화
				if(maxLeng < leng) maxLeng = leng;
				leng = 0;
			}
		}
		
		if(maxLeng < leng) maxLeng = leng;		// 모든 구간이 참일 경우를 위해 한번 더 최댓값 갱신
		
		System.out.println(maxLeng);	// 결과 값 출력
	}
}

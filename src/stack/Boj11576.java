package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11576번: Base Conversion
 *
 *	@see https://www.acmicpc.net/problem/11576/
 *
 */
public class Boj11576 {
	private static final String SPACE = " ";

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		int[] num = new int[m];
		for(int i = 0; i < m; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		int idx = 0;
		int tenary = 0;
		
		for (int i = num.length - 1; i >= 0; i--) {		// 입력 받은 진수를 10진수로 변경
			tenary += num[i] * (int) Math.pow(A, idx++);
		}

		Stack<Integer> lifo = new Stack<>();	
		while (tenary > 0) {				// 각 자리에 해당하는 값들을 하나씩 스택에 담고
			lifo.push(tenary % B);
			tenary /= B;
		}
		
		StringBuilder sb = new StringBuilder();
		while(!lifo.isEmpty()) sb.append(lifo.pop()).append(SPACE);		// 순서대로 꺼내어 자리별로 버퍼에 저장

		System.out.println(sb.toString());			// 결과 값 한번에 출력
	}
}

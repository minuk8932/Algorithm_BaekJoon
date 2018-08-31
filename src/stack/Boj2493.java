package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2493번: 탑
 *
 *	@see https://www.acmicpc.net/problem/2493/
 *
 */
public class Boj2493 {
	private static final char SPACE = ' ';
	private static final int NONE = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		Stack<Sign> lifo = new Stack<>();
		int[] tower = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i < N + 1; i++) {
			tower[i] = Integer.parseInt(st.nextToken());
			
			while(!lifo.isEmpty()) {						// 스택에 탑이 존재 할 때
				if(lifo.peek().high > tower[i]) {			// 가장 위의 탑의 높이가 현재 입력으로 들어온 탑의 높이보다 크면
					sb.append(lifo.peek().idx).append(SPACE);		// 수신 가능이므로 스택 내 탑의 순서를 버퍼에 담고 반복문 종료 
					break;
				}
				
				lifo.pop();			// 높이가 작다면, 그대로 스택에서 꺼내줌
			}
			
			if(lifo.isEmpty()) sb.append(NONE).append(SPACE);	// 스택이 비어있는 상태라면 버퍼에 0을 입력
			lifo.push(new Sign(tower[i], i));			// 입력으로 들어온 값을 스택에 푸쉬
		}
		
		System.out.println(sb.toString());				// 결과 값 한번에 출력
	}
	
	/**
	 * 타워 신호 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Sign{
		int high;
		int idx;
		
		public Sign(int high, int idx) {
			this.high = high;
			this.idx = idx;
		}
	}
}

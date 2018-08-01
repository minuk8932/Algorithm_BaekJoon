package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 백준 6198번: 옥상 정원
 * @author minchoba
 *
 * @see https://www.acmicpc.net/problem/6198/
 *
 */
public class Boj6198 {
    private static final int EMPTY = -1;
    
	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> stk = new Stack<>();

		long res = 0;

		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(br.readLine());

			if (stk.isEmpty()) {	// 스택이 빈 경우
				stk.push(height);
			} 
			else {
				int tmp = 0;
				
				while ((tmp = stk.pop()) <= height) {	// 현재 들어올 높이가 스택의 가장 상단의 값보다 크거나 같으면, 스택내부에 값들을 계속 꺼냄	
					if (stk.isEmpty()) {		// 꺼내다 스택이 비어버린경우
						stk.push(height);		// 현재 높이를 스택에 담고, 높이가 비었음을 표시 후 종료
						height = EMPTY;
						break;
					}
				}
				
				if(height != EMPTY) {		// 높이가 빈적이 없는 경우
					stk.push(tmp);			// 꺼냈던 상단의 값과 그 다음 현재의 높이를 차례로 스택에 담음
					stk.push(height);
				}
			}

			res += (stk.size() - 1);		// 각 경우마다, 스택의 크기 - 1의 값을 변수에 더함 (두수의 비교가 필요하므로)
		}

		System.out.println(res);		// 결과 값 출력
	}
}

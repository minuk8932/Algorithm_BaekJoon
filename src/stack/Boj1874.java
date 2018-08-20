package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 
 * 	@author minchoba
 *	 백준 1874번: 스택 수열
 *
 *	@see https://www.acmicpc.net/problem/1874/
 *
 */
public class Boj1874 {	
	private static final String PUSH = "+\n";
	private static final String POP = "-\n";
	private static final String CAN_NOT = "NO\n";
    
    private static Stack<Integer> lifo = new Stack<>();
    private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] seq = new int[N + 1];
		for(int i = 1; i < N + 1; i++) seq[i] = Integer.parseInt(br.readLine());
		
		makeSequence(seq, N + 1);		// 수열 생성 메서드 실행
	}
	
	/**
	 * 수열 생성 메서드
	 * 
	 */
	private static void makeSequence(int[] arr, int limit) {
		int cursor = 1;
		int val = 1;
		boolean isNO = false;	// NO를 출력해야 하는가?
		
		lifo.push(val++);		// 처음 값 스택으로 푸쉬
		sb.append(PUSH);
		
		while(cursor < limit) {			// 배열의 모든 인덱스를 탐색 완료하면 종료	
			if(lifo.isEmpty()) {		// 실행중 스택이 빈 상태가 되면 무조건 다음 값 푸쉬
				lifo.push(val++);
				sb.append(PUSH);
				
				continue;
			}
			
			if(lifo.peek() == arr[cursor]) {	// 현재 찾는 배열의 값과 스택의 맨 위의 값이 같은경우
				lifo.pop();
				sb.append(POP);				// pop을 하며 +출력 후 다음 인덱스로 넘어감
				cursor++;
			}
			else{							// 값이 다르면 다음값 푸쉬
			    lifo.push(val++);
			    sb.append(PUSH);
            }
			
			if(val > limit) {				// 값이 지정된 값보다 커진 경우 반복문 종료
				isNO = true;
				break;
			}
		}
		
		if(!lifo.isEmpty()) isNO = true;		// 모든 프로세스가 종료되었지만 스택에 값이 남은 경우
		
		System.out.println(isNO ? CAN_NOT : sb.toString());		// isNO가 참이면 NO 출력, 아니면 버퍼의 결과값 한번에 출력
	}
}

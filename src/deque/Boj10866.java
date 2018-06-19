package deque;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10866번: 덱
 *
 *	@see https://www.acmicpc.net/problem/10866/
 *
 */
public class Boj10866 {
	private static final String NEW_LINE = "\n";
	private static final String PUSH_F = "push_front";
	private static final String PUSH_B = "push_back";
	private static final String POP_F = "pop_front";
	private static final String POP_B = "pop_back";
	private static final String SIZE = "size";
	private static final String EMPTY = "empty";
	private static final String F = "front";
	private static final String B = "back";
	
	private static final int INF = 20_001;
	private static final int IS_EMPTY = -1;
	private static final int DEQUE_E = 1;
	private static final int DEQUE_F = 0;
	
	private static int[] deque = new int[INF];
	private static int cursor = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			int num = 0;
			
			try {		// push의 경우 예외처리
				num = Integer.parseInt(st.nextToken());
			}
			catch(Exception e) {	
			}
			
			int res = 0;
			
			switch (command) {	// 들어온 명령문에 따라 함수 호출
			case PUSH_F:
				pushFront(num);
				break;
				
			case PUSH_B:
				pushBack(num);
				break;
				
			case POP_F:
				res = popFront();
				break;
				
			case POP_B:
				res = popBack();
				break;
			
			case SIZE:
				res = size();
				break;
				
			case EMPTY:
				res = empty();
				break;
				
			case F:
				res = front();
				break;
				
			case B:
				res = back();
				break;
			}
			
			if(num == 0) sb.append(res).append(NEW_LINE);	// num의 입력이 들어온 경우만 res값을 버퍼에 담음
		}
		
		System.out.println(sb.toString());					// 결과값 한번에 출력
	}
	
	private static void pushFront(int num) {		// 앞쪽으로 값 offer
		int cnt = 0;
		
		if(deque[0] != 0) {						// 0번 인덱스에 값이 존재하는 경우
			for(int i = 10000; i >= 0; i--) {
				if(deque[i] != 0) {				// 배열값이 0이 아닐때까지 뒤로 한칸씩 땡기고
					deque[i + 1] = deque[i];
					cnt++;						// 반복문 진행 횟수를 구함
				}
			}
		}
		cursor = cnt + 1;		// 진행 횟수 +1을 cursor로 설정
		deque[0] = num;			// 0번째 인덱스에 값 offer
	}
	
	private static void pushBack(int num) {		// 뒷쪽으로 값 offer
		deque[cursor] = num;			// 커서가 가리키는 위치에 값 offer
		cursor++;						// 커서 +1
	}
	
	private static int popFront() {		// 가장 앞의 값을 poll
		int pop = 0;
		
		if(deque[0] == 0) pop = IS_EMPTY;	// 덱이 빈경우 -1
		else {
			pop = deque[0];
			for(int i = 1; i < cursor + 1; i++) {		// 값이 존재하면 가장 앞의 값을 저장 후
				deque[i - 1] = deque[i];		// 1번 인덱스부터 앞으로 댕겨주는 연산을 수행
			}
			
			cursor--;			// 앞으로 하나씩 댕겼다면 커서 -1
		}
		
		return pop;				// poll한 값을 반환
	}
	
	private static int popBack() {			// 가장 뒤의 값을 poll
		int pop = 0;

		if(deque[0] == 0) pop = IS_EMPTY;	// 덱이 빈경우 -1
		else {
			pop = deque[cursor - 1];		// 값이 존재하면 가장 뒤의 값을 저장 후
			deque[cursor - 1] = 0;		// 커서 바로 전번 인덱스를 0으로 바꿈(poll)
			cursor--;					// 값이 하나 빠졌으므로 커서 -1 
		}
		
		return pop;				// poll한 값 반환
	}
	
	private static int size() {		// 커서가 가리키는 인덱스가 곧 덱의 사이즈
		return cursor;
	}
	
	private static int empty() {	// 커서가 0인 경우 빈 인덱스이고, 0이 아니면 빈 것이 아님
		return cursor == 0 ? DEQUE_E : DEQUE_F;
	}
	
	private static int front() {	// 덱의 가장 앞부분 값을 반환, 빈 덱이라면 -1반환
		return deque[0] == 0 ? IS_EMPTY : deque[0];
	}
	
	private static int back() {		// 덱의 가장 뒷부분 값을 반환, 빈 덱이라면 -1반환
		int val = cursor != 0 ? deque[cursor - 1] : deque[cursor];	// 커서값이 0인 경우엔 인덱스를 댕기지않은 채로 해당 값을 val에 저장
		return val == 0 ? IS_EMPTY : val;
	}
}

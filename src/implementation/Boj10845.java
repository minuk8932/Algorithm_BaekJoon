package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10845번: 큐
 *
 *	@see https://www.acmicpc.net/problem/10845/
 *
 */
public class Boj10845 {
	private static final String PUSH = "push";
	private static final String POP = "pop";
	private static final String SIZE = "size";
	private static final String EMPTY = "empty";
	private static final String FRONT = "front";
	private static final String BACK = "back";
	private static final String NEW_LINE = "\n";
	
	private static final int INF = 10_001;
	private static int[] queue = new int[INF];
	private static int cursor = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(N-- > 0) {
			String line = br.readLine();		// 입력 한줄 받고
			String command = null;
			int num = 0;
			
			try {
				StringTokenizer st = new StringTokenizer(line);		// 숫자도 같이 입력으로 들어온 경우
				command = st.nextToken();
				num = Integer.parseInt(st.nextToken());
			}
			catch(Exception e) {
			}
			
			command = num == 0 ? line : command;		// 조건에 따라 명령어 변수에 값을 담음
			
			switch (command) {						// case 별 해당 메서드 호출 후 반환값이 있는경우 버퍼에 담아줌
			case PUSH:
				push(num);
				break;
				
			case POP:
				sb.append(pop()).append(NEW_LINE);
				break;
				
			case SIZE:
				sb.append(size()).append(NEW_LINE);
				break;
				
			case EMPTY:
				sb.append(empty()).append(NEW_LINE);
				break;
				
			case FRONT:
				sb.append(front()).append(NEW_LINE);
				break;
				
			case BACK:
				sb.append(back()).append(NEW_LINE);
				break;
			}
		}
		
		System.out.println(sb.toString());			// 결과값 한번에 출력
	}
	
	/**
	 * 	큐에 값을 offer하는 메소드
	 * 	@param a
	 */
	private static void push(int a) {
		queue[cursor] = a;
		cursor++;
	}
	
	/**
	 * 큐에 존재하는 가장 앞자리 값을 pop(꺼내옴)하는 메소드
	 * @return 맨 앞의 값
	 */
	private static int pop() {
		int val;
		int size = size();
		
		if(queue[0] != 0) {		// 가장 앞에 데이터가 들은경우
			val = queue[0];		// 데이터를 val 변수에 담고
			queue[0] = 0;		// 맨 앞 배열을 0(비어있음)처리
			
			for(int i = 1; i < size; i++) {		// 포문을 돌면서 사이즈에 맞게, 한칸씩 데이터를 앞으로 땡겨줌
				queue[i - 1] = queue[i];
				queue[i] = 0;
			}
			
			cursor--;			// 데이터가 추가되어야 할 인덱스를 앞으로 한칸 땡겨줌
		}
		else {
			val = -1;			// 큐에 데이터가 없는 경우 -1
		}
		
		return val;				// pop된 값 반환
	}
	
	/**
	 * 큐의 크기를 반환하는 메소드
	 */
	private static int size() {
		int length = 0;
		
		for(int i = 0; i < queue.length; i++) {		// 0이 나오기 전까지 길이 +1
			if(queue[i] == 0) break;
			length++;
		}
		
		return length++;		// 큐의 사이즈 반환
	}
	
	/**
	 * isEmpty?
	 */
	private static int empty() {
		return queue[0] == 0 ? 1 : 0;	// 큐의 첫번째 값이 0이면 비어있음, 아니면 데이터가 채워져있음
	}
	
	/**
	 * 큐의 맨 앞자리 값을 읽어주는 메소드
	 * @return 맨앞의 값
	 */
	private static int front() {		
		int val = 0;
		if(queue[0] != 0) {		// 맨앞에 값이 있다면, 그 값을 val에, 큐가 비어있다면 -1
			val = queue[0];
		}
		else {
			val = -1;
		}
		
		return val;
	}
	
	/**
	 * 큐의 맨 뒷자리 값을 읽어주는 메소드
	 * @return 맨뒤의 값
	 */
	private static int back() {
		int val = 0;
		int size = size();
		
		if(queue[0] != 0) {		// 맨뒤에 값이 있다면, 그 값을 val에, 큐가 비어있다면 -1
			val = queue[size - 1];
		}
		else {
			val = -1;
		}
		
		return val;
	}
}

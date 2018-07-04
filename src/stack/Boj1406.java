package stack;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1406번: 에디터
 *
 *	@see https://www.acmicpc.net/problem/1406/
 *
 */
public class Boj1406 {
	private static final char PUSH = 'D';
	private static final char PULL = 'L';
	private static final char REMOVE = 'B';
	private static final char ADD = 'P';
	
	// 버퍼를 통한 입출력
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception{		
		Stack<Character> tmp = new Stack<>();		// 임시 스택
		Stack<Character> res = new Stack<>();		// 결과 스택
		
		String line = br.readLine();				// 스택의 나눠진 모양에 따라 커서의 위치가 결정됨
		for(char word : line.toCharArray()) tmp.push(word);
		
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char cmd = st.nextToken().charAt(0);
			
			if((cmd == PULL || cmd == REMOVE) && tmp.isEmpty()) continue;	// 커서 왼쪽 이동 또는 단어 하나 제거시, 임시 스택이 비었다면, 다음 명령으로 넘어감
			else if(cmd == PUSH && res.isEmpty()) continue;		// 커서 오른쪽 이동시 결과 스택이 비었다면, 다음 명령으로 넘어감
			
			switch (cmd) {
			case PUSH:				// 오른쪽으로
				tmp.push(res.pop());		// 커서가 오른쪽으로 가므로, 최종 스택에서 넘겨줌
				break;
				
			case PULL:				// 왼쪽으로
				res.push(tmp.pop());		// 커서가 왼쪽으로 가므로, 임시 스택에서 넘겨줌
				break;
			
			case REMOVE:			// 단어하나 제거
				tmp.pop();
				break;
					
			case ADD:				// 단어하나 추가
				char w = st.nextToken().charAt(0);
				tmp.push(w);
				break;
			}	
		}
		
		br.close();
		
		while(!tmp.isEmpty()) {		// 임시 스택의 값을 최종 스택에 담고
			res.push(tmp.pop());
		}
		
		while(!res.isEmpty()){
			bw.write(res.pop());	// LIFO에 따라 뽑아내며 버퍼에 각 값을 담아줌
		}
		
		bw.flush();		// 결과값 한번에 출력
	}
}

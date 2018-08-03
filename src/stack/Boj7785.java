package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 7785번: 회사에 있는 사람
 *
 *	@see https://www.acmicpc.net/problem/7785/
 *
 */
public class Boj7785 {
	private static final String STAY = "enter";
	private static final String LEAVE = "leave";
	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Member[] m = new Member[n];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			m[i] = new Member(st.nextToken(), st.nextToken());		// 회사원의 이름과 상태를 클래스 배열에 저장 
		}
		
		Arrays.sort(m);				// 이름순으로 정렬
		
		Stack stk = new Stack<>();
		
		for(int i = 0; i < n; i++) {
			if(m[i].state.equals(STAY)) {		// 들어와있는 회사원의 경우, 스택에 저장
				stk.push(m[i].name);
			}
			
			if(!stk.isEmpty()) {						// 스택이 비어있지 않을 때
				if(m[i].state.equals(LEAVE)) {			// 나간 회사원이 있으면
					if(stk.peek().equals(m[i].name)) {	// 스택의 가장 위의 이름이 나간 회사원의 이름과 같다면
						stk.pop();				// 스택의 이름을 빼줌
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(!stk.isEmpty()) sb.append(stk.pop()).append(NEW_LINE);	// 스택의 값들을 하나씩 빼면서 버퍼에 담음 (역순으로 담겨짐)
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
	
	/**
	 * 멤버 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Member implements Comparable<Member>{
		String name;
		String state;
		
		public Member(String name, String state) {
			this.name = name;
			this.state = state;
		}

		@Override
		public int compareTo(Member m) {
			return this.name.compareTo(m.name);
		}
	}
}

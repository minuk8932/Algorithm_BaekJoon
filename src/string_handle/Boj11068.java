package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * 	@author minchoba
 *	백준 11068번: 회문인 수
 *
 *	@see https://www.acmicpc.net/problem/11068/
 *
 */
public class Boj11068 {
	private static final int TRUE = 1;
	private static final int FALSE = 0;
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			String N = br.readLine();
			char[] num = N.toCharArray();
			
			boolean isPalindrome = true;
			
			for(int i = 0; i < num.length / 2; i++) {		// 10진수 팰린드롬인가?
				if(num[i] != num[num.length - 1 - i]) {
					isPalindrome = false;
					break;
				}
			}
			
			if(isPalindrome) {						// 10진수 팰린드롬인 경우 버퍼에 1 저장
				sb.append(TRUE).append(NEW_LINE);
			}
			else {									// 그 외
				if(makePalindrome(N)) sb.append(TRUE).append(NEW_LINE);	// makePalindrome 메소드를 통해 팰린드롬 여부 판별
				else sb.append(FALSE).append(NEW_LINE);
			}
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}

	/**
	 * 진법에 따른 팰린드롬 여부 확인
	 * 
	 */
	private static boolean makePalindrome(String n) {
		int num = Integer.parseInt(n);
		Deque<Integer> deq = new LinkedList<>();
		
		for(int i = 2; i <= 64; i++) {
			int tmp = num;
			
			while(tmp > 0) {
				deq.offerFirst(tmp % i);	// 덱에 해당 숫자의 i진법의 값을 담아줌
				tmp /= i;
			}
			
			while(deq.size() > 1) {							// 덱에 모든 값을 담은 후
				if(deq.peekFirst() != deq.peekLast()) break;	// 만약 양쪽 끝 값이 다르면, 반복문 종료
				deq.pollFirst();								// 같은 경우 양쪽으로 하나씩 제거
				deq.pollLast();
			}
			
			if(deq.size() < 2) return true;			// 덱에 남아있는 값의 수가 2개 미만인 경우 팰린드롬
			deq.clear();					// 아닌 경우엔 덱을 비우고 다음 진법으로 다시 검사
		}
		
		return false;				// 모든 진법 검사 후에도 메소드가 종료되지 않으면 거짓 반환
	}
}

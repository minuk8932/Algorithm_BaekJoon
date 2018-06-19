package deque;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5430번: AC
 *
 *	@see https://www.acmicpc.net/problem/5430/
 *
 */
public class Boj5430 {
	private static final String SEPARATE = ",";
	private static final String NEW_LINE = "\n";
	private static final String ERROR = "error";

	private static final char REV = 'R';
	private static final char DEL = 'D';
	private static final char PRE = '[';
	private static final char POS = ']';

	private static Deque<Integer> nums = null;
	private static boolean isForward = false;
	private static boolean isError = false;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			String line = br.readLine();

			char[] command = line.toCharArray();
			int N = Integer.parseInt(br.readLine());
			
			nums = new LinkedList<>();
			
			String arr = br.readLine();

			StringTokenizer st = new StringTokenizer(arr.substring(1, arr.length() - 1), SEPARATE);	// 괄호와 콤마를 빼고 분할한 숫자 입력
			for (int i = 0; i < N; i++) {
				int tmp = Integer.parseInt(st.nextToken());
				nums.offer(tmp);
			}

			isForward = true;		// R이 들어왔는가?
			isError = false;		// Error 출력인가?
			
			for(int i = 0; i < command.length; i++) {
				if(command[i] == REV) {		// 정방향, 역방향을 서로 바꾸어줌
					isForward = !isForward;
					continue;
				}
				
				if(command[i] == DEL) {		// 방향 기준으로 원소 삭제
					if(nums.isEmpty()) {	// 삭제 연산이 들어왔는데 배열이 빈 경우 에러는 참으로 변경 후 반복문 종료
						isError = true;
						break;
					}
					
					if(isForward) nums.pollFirst();	// 그 외 정방향 원소 제거
					else nums.pollLast();			// 역방향 원소 제거
				}
			}
			
			if(isError) {							// 에러를 버퍼에 담고 다음 테스트 케이스 진행
				sb.append(ERROR).append(NEW_LINE);
				continue;
			}
			
			int size = nums.size();
			sb.append(PRE);
			
			while(!nums.isEmpty()) {			// 덱이 빌때까지 진행
				if(isForward) sb.append(nums.pollFirst());	// 정방향의 경우 pollFirst
				else sb.append(nums.pollLast());			// 역방향의 경우 pollLast
				
				sb.append(SEPARATE);
			}
			
			if(size > 0) {
				sb.deleteCharAt(sb.length() - 1);	// 덱 내에 원소가 존재했을 경우 맨뒤 콤마 제거
			}
			sb.append(POS).append(NEW_LINE);
		}

		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}

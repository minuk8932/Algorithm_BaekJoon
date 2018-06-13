package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 11637번: 인기투표
 *
 *	@see https://www.acmicpc.net/problem/11637/
 *
 */
public class Boj11637 {
	private static final String MAJOR = "majority winner ";
	private static final String MINOR = "minority winner ";
	private static final String NONE = "no winner";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			int[] candidate = new int[n + 1];
			
			int sum = 0, max = 0, winIdx = 0;
			boolean isNoWinner = false, isOver = false;
			
			for(int i = 1; i < n + 1; i++) {
				candidate[i] = Integer.parseInt(br.readLine());
				sum += candidate[i];		// 후보자 투표수의 총 합
				
				if(max < candidate[i]) {	// 최댓값 저장 후 그 값을 가지는 후보의 번호도 저장
					max = candidate[i];
					winIdx = i;
				}
			}
			
			for(int i = 1; i < n + 1; i++) {
				if(i != winIdx && max == candidate[i]) {	// 만약 공동 우승자가 있다면 isNoWinner 값을 참으로
					isNoWinner = true;
				}
			}
			
			isOver = max > sum / 2 ? true : false;		// 과반수가 넘는지 못넘는지 확인

			if(isNoWinner) {						// 승자가 없는경우
				sb.append(NONE).append(NEW_LINE);
			}
			else {
				if(isOver) {											// 과반수를 넘는 경우
					sb.append(MAJOR).append(winIdx).append(NEW_LINE);
				}
				else {													// 과반수를 넘지 못하느 경우
					sb.append(MINOR).append(winIdx).append(NEW_LINE);
				}
			}
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}

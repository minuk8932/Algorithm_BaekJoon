package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10162번: 전자레인지
 *
 *	@see https://www.acmicpc.net/problem/10162/
 *
 */
public class Boj10162 {
	private static final int MINUTES_FIVE = 300;
	private static final int MINUTE = 60;
	private static final int SECONDS_TEN = 10;
	private static final int CAN_NOT = -1;
	private static final int NONE = 0;
	
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		boolean isPossible = T % SECONDS_TEN == 0 ? true : false;		// 1의 자리 초단위가 존재하는 경우
		
		int A = (T >= MINUTES_FIVE) ? 
				T / MINUTES_FIVE : NONE;								// 주어진 초단위가 5분이 넘는 경우, 그 외 0
		
		int B = (T >= MINUTE) ? 
				(T % MINUTES_FIVE) / MINUTE : NONE;						// 주어진 초단위가 5분은 넘지 못하지만 1분은 넘는 경우, 그 외 0
				
		int C = (T >= SECONDS_TEN) ? 
				(T % MINUTES_FIVE) % MINUTE / SECONDS_TEN : NONE;		// 주어진 초단위가 1분은 넘지 못하지만 10초는 넘는 경우, 그 외 0
		
		if(isPossible) {					// 예약가능한 시간이면
			sb.append(A).append(SPACE).append(B).append(SPACE).append(C);	// 결과를 버퍼에 담아줌
		}
		else {
			sb.append(CAN_NOT);			// 예약 불가능한 경우
		}
		
		System.out.println(sb.toString());		// 버퍼의 결과값을 출력
	}
}

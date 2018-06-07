package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1408번: 24
 *
 *	@see https://www.acmicpc.net/problem/1408/
 *
 */
public class Boj1408 {
	private static final String COLON = ":";
	private static StringBuilder sb = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), COLON);
		// 첫번째 시간의 초단위
		int total1 = Integer.parseInt(st.nextToken()) * 3600 + Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), COLON);
		// 두번째 시간의 초단위
		int total2 = Integer.parseInt(st.nextToken()) * 3600 + Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
		
		int diff = total2 - total1;	// 두 시간의 차
		
		if(diff < 0) {				// 차가 0보다 작다면 순회 시켜줌
			diff += (24 * 3600);
		}
		
		int hour = diff / 3600;		// 해당 차이의 시간단위 계산
		
		if(hour > 23) {			// 시간값이 23보다 크다면 24로 나눈 나머지로 시간을 설정
			hour %= 24;
		}

		int min = (diff % 3600) / 60;		// 분 단위 계산
		int sec = (diff % 3600) % 60;		// 초 단위 계산
		
		getTime(hour);		// getTime 메서드를 이용한 시 분 초 계산
		getTime(min);
		getTime(sec);
		
		System.out.println(sb.substring(0, sb.length() - 1).toString());	// 버퍼의 내용에서 맨끝자리 ":"을 제외한 나머지 한번에 출력
	}
	
	private static void getTime(int t) {		// 입력된 숫자를
		if(t / 10 == 0) {					// 10으로 나눈 몫이 없다면
			if(t % 10 == 0) {					// 1의 자리계산
				sb.append("00").append(COLON);	// 1의 자리 몫 또한 없다면 00
			}
			else {
				sb.append("0" + t).append(COLON);	// 있다면 0t
			}
		}
		else {								// 십의자리와 일의자리 모두 0이 아닌 경우 그대로 버퍼에 담음
			sb.append(t).append(COLON);
		}
	}
}

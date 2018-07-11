package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2138번: 전구와 스위치
 *
 *	@see https://www.acmicpc.net/problem/2138/
 *
 */
public class Boj2138 {
	private static final char ON = '1';

	private static boolean[] curr = null;
	private static boolean[] currTwo = null;
	private static boolean[] next = null;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		curr = new boolean[N + 2];
		currTwo = new boolean[N + 2];
		next = new boolean[N + 2];

		char[] c = br.readLine().toCharArray();
		char[] n = br.readLine().toCharArray();

		for (int i = 1; i < N + 1; i++) {
			if (c[i - 1] == ON) curr[i] = currTwo[i] = true;	// 문자가 1인경우 참으로 변경
			if (n[i - 1] == ON)	next[i] = true;
		}
		
		System.out.println(click(N));		// 클릭 메소드를 통한 눌린 횟수 출력
	}
	
	/**
	 * 버튼 클릭 메소드
	 * 1번시작, 2번시작 두가지 경우로 나누어 조건을 고려
	 */
	private static int click(int N) {
		int clicked = 0;		// 1번 시작시
		int clickedTwo = 0;		// 2번 시작시
		
		for(int i = 1; i < N + 1; i++) {
			if(i > 1 && curr[i - 1] == next[i - 1]) continue;	// 2번부터 가장 앞의 전구 값이 서로 같다면, 버튼 클릭없이 다음으로 넘어감
			
			curr[i] = !curr[i];					// 그 외, 1번부터 순차적으로 버튼을 눌러줌
			curr[i - 1] = !curr[i - 1];
			curr[i + 1] = !curr[i + 1];
			clicked++;
		}
		
		for(int i = 2; i < N + 1; i++) {
			if(currTwo[i - 1] == next[i - 1]) continue;		// 가장 앞의 전구 값이 서로 같다면, 버튼 클릭없이 다음으로 넘어감

			currTwo[i] = !currTwo[i];			// 그 외, 2번부터 순차적으로 버튼을 눌러줌
			currTwo[i - 1] = !currTwo[i - 1];
			currTwo[i + 1] = !currTwo[i + 1];
			clickedTwo++;
		}
		
		boolean isSame = true, isSameTwo = true;
		for(int i = 1; i < N + 1; i++) {				// 각 전구들이 적당한 클릭으로 원하는 모양대로 켜졌는지 확인
			if(next[i] != curr[i]) isSame = false;
			if(next[i] != currTwo[i]) isSameTwo = false;
		}
		
		int res = -1;
		
		if(isSame) res = clicked;			// 1번 시작으로 원하는 모양이 가능한 경우
		if(isSameTwo) res = clickedTwo;		// 2번 시작으로 원하는 모양이 가능한 경우
		if(isSame && isSameTwo) res = Math.min(clicked, clickedTwo);	// 둘 다 가능하다면 둘 중 최솟값을 결과 변수에 저장
		
		return res;		// 위의 세가지 경우 모두 포함되지 않으면 -1, 그 외엔 최소 버튼 클릭 횟수를 반환
	}
}

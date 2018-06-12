package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11970번: 페인팅
 *
 *	@see https://www.acmicpc.net/problem/11970/
 *
 */
public class Boj11970 {
	private static final int INF = 101;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		int[] fence = new int[INF];		// 울타리 범위 지정 배열
		boolean isDup = false;			// 겹치는 부분이 있는지 확인
		
		for(int i = a; i < b + 1; i++) {		// 첫줄 범위 체크
			fence[i]++;
		}
		
		for(int i = c; i < d + 1; i++) {		// 둘째줄 범위 체크
			fence[i]++;
			
			if(fence[i] == 2) {			// 체크 중 겹치는 부분이 있으면 true로 변경
				isDup = true;
			}
		}
		
		int res = 0;
		
		if(isDup) {						// 겹치는 경우
			int min = Math.min(a, c);
			int max = Math.max(b, d);
			
			res = max - min;
		}
		else {						// 겹치지 않는 경우
			res = b - a + d - c;
		}
		
		System.out.println(res);	// 최종 칠해야 할 울타리 길이
	}
}

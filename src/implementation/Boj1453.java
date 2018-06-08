package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1453번: 피시방 알바
 *
 *	@see https://www.acmicpc.net/problem/1453/
 *
 */
public class Boj1453 {
	private static final int INF = 101;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] seat = new boolean[INF];		// 최대 좌석 수 만큼 배열 설정
		
		int guest = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N + 1; i++) {
			int idx = Integer.parseInt(st.nextToken());
			
			if(seat[idx]) {		// 이미 앉아있는 좌석인 경우
				guest++;		// 거절당한 손님수 +1 후 다음으로 넘어감
				continue;
			}
			seat[idx] = true;		// 손님이 앉은 자석은 참으로 변경
		}
		
		System.out.println(guest);	// 거절당한 손님 수 출력
	}
}

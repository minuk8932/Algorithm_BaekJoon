package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15729번: 방탈출
 *
 *	@see https://www.acmicpc.net/problem/15729/
 *
 */
public class Boj15729 {
	private static final String ON = "1";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] rooms = new boolean[N + 2];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			if(ON.equals(st.nextToken())) {		// 변해야 할 방 상태를 받아옴
				rooms[i] = true;
			}
		}
		
		int click = 0;
		
		for(int i = 0; i < N; i++) {			// 원래 초기 상태의 방으로 만들어줌
			if(rooms[i]) {
				rooms[i] = !rooms[i];
				rooms[i + 1] = !rooms[i + 1];
				rooms[i + 2] = !rooms[i + 2];
				
				click++;
			}
		}
		
		System.out.println(click);	// 초기화 되는데 필요한 클릭 횟수 출력
	}
}

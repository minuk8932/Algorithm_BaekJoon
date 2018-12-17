package deque;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2346번: 풍선 터뜨리기
 *
 *	@see https://www.acmicpc.net/problem/2346/
 *
 */
public class Boj2346 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Balloon ball = new Balloon(0, 0);
		Balloon start = ball;

		StringTokenizer st = new StringTokenizer(br.readLine());
		br.close();
		
		for (int i = 1; i < N + 1; i++) {
			Balloon tmp = start;
			start.back = new Balloon(i, Integer.parseInt(st.nextToken()));		// 순서대로 하나씩 입력
			start = start.back;
			start.front = tmp;
		}
		
		Balloon end = start;
		start.back = ball.back;
		start = start.back;
		start.front = end;

		System.out.println(popping(N, ball, start));
	}
	
	private static class Balloon{
		int idx;
		int num;
		Balloon front;
		Balloon back;
		
		public Balloon(int idx, int num) {
			this.idx = idx;
			this.num = num;
		}
	}
	
	private static StringBuilder popping(int N, Balloon b, Balloon s) {
		StringBuilder sb = new StringBuilder();
		
		while (N-- > 0) {
			sb.append(s.idx).append(' ');
			
			int move = s.num;
			
			Balloon t = s.front;		// 빠진것 제거
			s.front.back = s.back;
			s.back.front = t;

			int loop = Math.abs(move);
			
			if (move < 0) {
				while(loop-- > 0) {		// 앞으로 댕기기
					s = s.front;
				}
			}
			else {
				while(loop-- > 0) {		// 뒤로 밀기
					s = s.back;
				}
			}
		}
		
		
		return sb;
	}
}

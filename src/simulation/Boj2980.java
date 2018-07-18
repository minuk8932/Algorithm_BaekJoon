package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 * 	백준 2980번: 도로와 신호등
 * 
 * 	@see https://www.acmicpc.net/problem/2980/
 * 
 */
public class Boj2980 {
	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		int timer = 0, start = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int pos = Integer.parseInt(st.nextToken());
			Sign s = new Sign(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			timer += pos - start;		// 경과시간 += (신호등 까지의 위치) - (출발 지점의 위치)
			start = pos;				// 출발자점을 현 신호등의 위치로 갱신

			int stop = timer % (s.r + s.g);	// 신호등 두개 값을 더한 값으로 경과시간을 나눈 후

			if (stop <= s.r) {			// 그 값이 빨간불이 지속되는 시간보다 작을 시, 정지
				timer += s.r - stop;		// 정지한 시간을 경과시간에 더해줌
			}
		}
		
		// 경과시간에 전체 길이에서 마지막 신호등의 위치를 제거한 값(마지막 신호등으로부터 목적지까지의 경로)을 더한 결과 출력
		System.out.println(timer + (L - start));	
	}
	
	/**
	 * 신호등 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Sign {
		int r;
		int g;
		
		public Sign(int r, int g) {
			this.r = r;
			this.g = g;
		}
	}
}

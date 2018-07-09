package counter_clock_wise;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2166번: 다각형의 면적
 *
 *	@see https://www.acmicpc.net/problem/2166/
 *
 */
public class Boj2166 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Point[] pos = new Point[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pos[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));	// 각 점을 Point 배열에 입력
		}
		
		double square = 0;
		for(int i = 0; i < N; i++) {
			int nextIdx = i + 1;
			
			if(i == N - 1) nextIdx = 0;	// 마지막은 n-1과 0번째 인덱스를 곱해야 함
			
			// 좌표평면에서 다각형의 넓이 구하는 공식
			square += (pos[i].x * pos[nextIdx].y - pos[nextIdx].x * pos[i].y);
		}
		
		// 음수인 경우 양수로 변경 후 2로 나눈 결과를 소수점 1까지 반올림하여 출력
		System.out.printf("%.1f", square > 0 ? (square / 2) : (square / -2));
	}
	
	/**
	 * 정점 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Point{
		double x;
		double y;
		
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
}

package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6600번: 원의 둘레
 *
 *	@see https://www.acmicpc.net/problem/6600/
 *
 */
public class Boj6600 {
	private static final double PI = 3.141592653589793;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String line = br.readLine();
			if(line == null) break;
			
			StringTokenizer st = new StringTokenizer(line);
			Point[] p = new Point[3];
			
			for(int i = 0; i < p.length; i++) {
				p[i] = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
			}
			
			// 외접원과 삼각형의 넓이 공식 이용: 4R = abc / S, (a,b,c는 각 변의 길이, R은 외접원의 반지름, S는 삼각형의 넓이)
			double abSin0 = Math.sqrt(
					(Math.pow(p[2].x - p[0].x, 2) + Math.pow(p[2].y - p[0].y, 2)) * (Math.pow(p[2].x - p[1].x, 2) + Math.pow(p[2].y - p[1].y, 2))
					- Math.pow(Math.abs((p[2].x - p[0].x) * (p[2].x - p[1].x) + (p[2].y - p[0].y) * (p[2].y - p[1].y)), 2)
					) * 100000.0;
			double abc = Math.sqrt(Math.pow(p[2].x - p[0].x, 2) + Math.pow(p[2].y - p[0].y, 2))
					* Math.sqrt(Math.pow(p[0].x - p[1].x, 2) + Math.pow(p[0].y - p[1].y, 2))
					* Math.sqrt(Math.pow(p[1].x - p[2].x, 2) + Math.pow(p[1].y - p[2].y, 2)) * 100000.0;
			
			double res = Math.round((abc / abSin0) * PI * 100.0) / 100.0;
			
			// 결과값 출력
			System.out.println(res);
		}
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

package counter_clock_wise;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17387번: 선분 교차2
 *
 *	@see https://www.acmicpc.net/problem/17387/	
 *
 */
public class Boj17387 {
	private static class Line{
		Point point1;
		Point point2;
		
		public Line(Point point1, Point point2) {
			this.point1 = point1;
			this.point2 = point2;
		}
	}
	
	private static class Point{
		long x;
		long y;
		
		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Line line1 = getLine(br.readLine());
		Line line2 = getLine(br.readLine());
		
		System.out.println(isCrossed(line1, line2));
	}
	private static Line getLine(String input) {
		StringTokenizer st = new StringTokenizer(input);
		
		return new Line(new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())), 
				new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())));
	}
	
	
	private static int CCW(Point a, Point b, Point c) {
		long front = a.x * b.y + b.x * c.y + c.x * a.y;
		long back = a.y * b.x + b.y * c.x + c.y * a.x;
		
		if(front == back) return 0;
		return front > back ? 1: -1;
	}
	
	private static int isCrossed(Line line1, Line line2) {
		int result1 = CCW(line1.point1, line1.point2, line2.point1) * CCW(line1.point1, line1.point2, line2.point2);
		int result2 = CCW(line2.point1, line2.point2, line1.point1) * CCW(line2.point1, line2.point2, line1.point2);
		
		if(result1 == 0 && result2 == 0) {
			long a = (line1.point1.x + line1.point1.y);
			long b = (line1.point2.x + line1.point2.y);
			long c = (line2.point1.x + line2.point1.y);
			long d = (line2.point2.x + line2.point2.y);
			
			if(a > b) {
				if(a > b) {				// swap
					long tmp = a;
					a = b;
					b = tmp;
				}
			}
				
			if(c > d) {
				if(c > d) {
					long tmp = c;
					c = d;
					d = tmp;
				}
			}
			
			return c <= b && a <= d ? 1: 0;			// is linear
		}
		
		return result1 <= 0 && result2 <= 0 ? 1: 0;		// is crossed
	}
}

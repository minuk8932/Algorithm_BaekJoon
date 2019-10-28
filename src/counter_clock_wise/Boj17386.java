package counter_clock_wise;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17386번: 선분 교차1
 *
 *	@see https://www.acimcpc.net/problem/17386/
 *
 */
public class Boj17386 {
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
		long front = a.x * b.y + b.x * c.y + c.x * a.y;				// watch out plz...
		long back = a.y * b.x + b.y * c.x + c.y * a.x;
		
		return front > back ? 1: -1;
	}
	
	private static int isCrossed(Line line1, Line line2) {
		int result1 = CCW(line1.point1, line1.point2, line2.point1) * CCW(line1.point1, line1.point2, line2.point2);
		int result2 = CCW(line2.point1, line2.point2, line1.point1) * CCW(line2.point1, line2.point2, line1.point2);
		
		return result1 < 0 && result2 < 0 ? 1: 0;				// is crossed?
	}
}

package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2527번: 직사각형
 *
 *	@see https://www.acmicpc.net/problem/2527/
 *
 */
public class Boj2527 {
	private static final String NEW_LINE = "\n";
	private static final char R = 'a';
	private static final char L = 'b';
	private static final char P = 'c';
	private static final char N = 'd';

	private static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static class Rectangle{
		Point p1;
		Point p2;

		public Rectangle(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int test = 0; test < 4; test++) {
			Rectangle[] sqr = new Rectangle[2];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int r = 0; r < 2; r++) {
				sqr[r] = new Rectangle(
						new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())),
						new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))
				);
			}

			Rectangle compress = new Rectangle(
					new Point(Math.max(sqr[0].p1.x, sqr[1].p1.x), Math.max(sqr[0].p1.y, sqr[1].p1.y)),
					new Point(Math.min(sqr[0].p2.x, sqr[1].p2.x), Math.min(sqr[0].p2.y, sqr[1].p2.y))
			);

			sb.append(judge(compress)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static char judge(Rectangle rec) {
		Point diff = new Point(rec.p2.x - rec.p1.x, rec.p2.y - rec.p1.y);

		if(diff.x > 0 && diff.y > 0) return R;
		else if(diff.x < 0 || diff.y < 0) return N;
		else if(diff.x == 0 && diff.y == 0) return P;
		return L;
	}
}

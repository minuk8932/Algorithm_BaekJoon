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
	
	private static class Rectangle{
		int x1;
		int y1;
		int x2;
		int y2;
		
		public Rectangle(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int test = 0; test < 4; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Rectangle[] sqr = new Rectangle[2];
			
			for(int r = 0; r < 2; r++) {
				sqr[r] = new Rectangle(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			sb.append(judge(sqr)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static char judge(Rectangle[] arr) {
		if(none(arr[0], arr[1])) return N;
		else if(point(arr[0], arr[1])) return P;
		else if(line(arr[0], arr[1])) return L;
		else return R;
	}
	
	private static boolean none(Rectangle r1, Rectangle r2) {			// 서로 다른 영역
		if(r1.x1 > r2.x2 || r1.x2 < r2.x1 || r1.y2 < r2.y1 || r2.y2 < r1.y1) return true;
		return false;
	}
	
	private static boolean point(Rectangle r1, Rectangle r2) {			// 접점 존재
		if((r1.x2 == r2.x1 && r1.y2 == r2.y1) || 
				(r1.x2 == r2.x1 && r1.y1 == r2.y2) || 
				(r1.x1 == r2.x2 && r1.y2 == r2.y1) || 
				(r1.x1 == r2.x2 && r1.y1 == r2.y2)) return true;
		return false;
	}
	
	private static boolean line(Rectangle r1, Rectangle r2) {			// 접선 존재
		if((r1.x2 == r2.x1 && r1.y2 != r2.y1) ||
				(r1.x1 == r2.x2 && r1.y2 != r2.y1) || 
				(r1.y1 == r2.y2 && r1.x2 != r2.x1) ||
				(r1.y1 == r2.y2 && r1.x1 != r2.x2)) return true;
		return false;
	}
}

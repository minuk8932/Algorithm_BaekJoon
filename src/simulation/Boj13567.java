package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13567번: 로봇
 *
 *	@see https://www.acmicpc.net/problem/13567/
 *
 */
public class Boj13567 {
	private static final String TURN = "TURN";
	private static final Point[] COMPASS = {new Point(0, 1), new Point(1, 0), new Point(0, -1), new Point(-1, 0)};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		Point dir = new Point(1, 0);
		Point start = new Point(0, 0);
		boolean isNull = false;
		
		while(n-- > 0) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			int deci = Integer.parseInt(st.nextToken());
			
			if(cmd.equals(TURN)) {
				dir = turnDirection(dir, deci * 2 - 1);	// 상수 배열을 통해 다음 방향 잡기
			}
			else {
				start = movingDirection(start.x + dir.x * deci, start.y + dir.y * deci, M); // 방향에 맞춰 움직이기
				
				if(start == null) {
					isNull = true;
					break;
				}
			}
		}
		System.out.println(isNull ? -1 : start.x + " " + start.y);
	}
	
	private static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static Point movingDirection(int x, int y, int m) {
		if(x < 0 || y < 0 || x >= m || y >= m) return null;
		return new Point(x, y);
	}
	
	private static Point turnDirection(Point origin, int turn) {
		int idx = (getIdx(origin) + turn) % 4;
		if(idx < 0) idx = 3;
		
		return new Point(COMPASS[idx].x, COMPASS[idx].y);
	}
	
	private static int getIdx(Point p) {
		for(int i = 0; i < COMPASS.length; i++) {
			if(p.x == COMPASS[i].x && p.y == COMPASS[i].y) {
				return i;
			}
		}
		
		return -1;
	}
}

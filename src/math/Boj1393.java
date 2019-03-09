package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1393번: 음하철도 구구팔
 *
 *	@see https://www.acmicpc.net/problem/1393/
 *
 */
public class Boj1393 {
	private static final String SPACE = " ";
	
	private static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Point station = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		st = new StringTokenizer(br.readLine());
		Point current = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		Point move = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		System.out.println(jump(station,  current, move));
	}
	
	private static StringBuilder jump(Point target, Point start, Point m) {
		StringBuilder sb = new StringBuilder();
		int min = getDistance(target, start);
		Point result = new Point(start.x, start.y);
		
		int gcd = Math.abs(getGcd(m.x, m.y));		// 서로소인 증가 값을 구해줌
		m.x /= gcd;
		m.y /= gcd;
		
		while(true) {
			if(start.x > 100 || start.y > 100 || start.x < -100 || start.y < -100) break;	// 입력 범위가 넘어가는 경우
			start.x += m.x;
			start.y += m.y;
			
			int distance = getDistance(target, start);
			if(distance < min) {						// 최단 거리의 지점 저장
				min = distance;
				result = new Point(start.x, start.y);
			}
		}

		return sb.append(result.x).append(SPACE).append(result.y);
	}
	
	private static int getGcd(int a, int b) {
		if(b == 0) return a;
		return getGcd(b, a % b);
	}
	
	private static int getDistance(Point p1, Point p2) {
		return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
	}
}

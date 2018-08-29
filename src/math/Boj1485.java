package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1485번: 정사각형
 *
 *	@see https://www.acmicpc.net/problem/1485/
 *
 */
public class Boj1485 {
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			Point[] p = new Point[4];
			for(int i = 0; i < p.length; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				p[i] = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
			}
			
			Arrays.sort(p);		// 좌표 정렬
			
			// 대각선 길이가 각각 같으면서, 4변의 길이가 모두 같은경우 버퍼에 1을 아니면 0을 담음
			sb.append(distance(p) && isSquare(p) ? 1 : 0).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());	// 결과 값 한번에 출력
	}
	
	/**
	 * 정점 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Point implements Comparable<Point>{
		long x;
		long y;
		
		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point p) {
			if(this.x < p.x) {
				return -1;
			}
			
			else if(this.x > p.x) {
				return 1;
			}
			else {
				if(this.y < p.y) return -1;
				else if(this.y > p.y) return 1;
				else return 0;
			}
		}
	}
	
	private static boolean distance(Point[] pos) {		// 가장 떨어진 점 두개의 대각선 각각의 길이 비교 계산 메소드
		long dist1 = (long) (Math.pow(pos[3].x - pos[0].x, 2) + Math.pow(pos[3].y - pos[0].y, 2));
		long dist2 = (long) (Math.pow(pos[2].x - pos[1].x, 2) + Math.pow(pos[2].y - pos[1].y, 2));
		
		return dist1 == dist2 ? true : false;
	}
	
	private static boolean isSquare(Point[] pos) {		// 사각형 네변의 길이 비교 메소드
		long line1 = (long) (Math.pow(pos[1].x - pos[0].x, 2) + Math.pow(pos[1].y - pos[0].y, 2));
		long line2 = (long) (Math.pow(pos[3].x - pos[1].x, 2) + Math.pow(pos[3].y - pos[1].y, 2));
		long line3 = (long) (Math.pow(pos[2].x - pos[0].x, 2) + Math.pow(pos[2].y - pos[0].y, 2));
		long line4 = (long) (Math.pow(pos[3].x - pos[2].x, 2) + Math.pow(pos[3].y - pos[2].y, 2));
		
		return (line1 == line2 && line2 == line3 && line3 == line4 && line4 == line1) ? true : false;
	}
}

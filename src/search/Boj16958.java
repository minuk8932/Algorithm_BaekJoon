package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16958: 텔레포트
 *
 *	@see https://www.acmicpc.net/problem/16958
 *
 */
public class Boj16958 {
	private static final String NEW_LINE = "\n";
	
	private static class Point{
		int x;
		int y;
		boolean isSpecial;
		
		public Point(int x, int y, boolean isSpecial) {
			this.x = x;
			this.y = y;
			this.isSpecial = isSpecial;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		Point[] city = new Point[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int code = Integer.parseInt(st.nextToken());
			city[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), code == 1 ? true : false);
		}
		
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			sb.append(minDistance(city, from, to, T)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int minDistance(Point[] p, int edge1, int edge2, int timer) {
		int plainDistance = getManhattanDistance(p[edge1], p[edge2]);
		
		if(p[edge1].isSpecial && p[edge2].isSpecial) {	// 두 도시 모두 스페셜인 경우
			return Math.min(timer, plainDistance);
		}
		else {
			if(p[edge1].isSpecial) {					// 스페셜이 아닌 도시에서 스페셜까지의 거리를 구함
				int cost = findSpecial(p, edge2);
				cost += timer;
				
				return Math.min(plainDistance, cost);
			}
			else if(p[edge2].isSpecial) {
				int cost = findSpecial(p, edge1);
				cost += timer;
				
				return Math.min(plainDistance, cost);
			}
			else {										// 경유 가능한 스페셜 도시를 찾음
				int cost = findSpecial(p, edge1) + findSpecial(p, edge2);
				
				return Math.min(plainDistance, cost + timer);
			}
		}
	}
	
	private static int findSpecial(Point[] p, int origin) {
		int min = Integer.MAX_VALUE;
		
		for(int i = 1; i < p.length; i++) {
			if(i != origin && p[i].isSpecial) {
				min = Math.min(min, getManhattanDistance(p[i], p[origin]));
			}
		}
		
		return min;
	}
	
	private static int getManhattanDistance(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
}

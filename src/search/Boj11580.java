package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * 
 * 	@author minchoba
 *	백준 11580번: Footprint
 *
 *	@see https://www.acmicpc.net/problem/11580/
 *
 */
public class Boj11580 {
	private static HashMap<Character, Point> hm = new HashMap<>();
	
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
		br.readLine();
		init();
		
		char[] cmd = br.readLine().toCharArray();
		System.out.println(getCount(cmd));
	}
	
	private static int getCount(char[] arr) {
		boolean[][] print = new boolean[2_001][2_001];
		int count = 1;
		
		Point start = new Point(1_000, 1_000);
		print[start.x][start.y] = true;
		
		for(char dir: arr) {
			Point next = new Point(start.x + hm.get(dir).x, start.y + hm.get(dir).y);
			start = new Point(next.x, next.y);
			
			if(print[next.x][next.y]) continue;		// 이미 밟은 위치는 갯수를 세지 않음
			print[next.x][next.y] = true;
			count++;
		}
		
		return count;
	}
	
	private static void init() {			// 방향 정보
		hm.put('N', new Point(0, 1));
		hm.put('S', new Point(0, -1));
		hm.put('E', new Point(1, 0));
		hm.put('W', new Point(-1, 0));
	}
}

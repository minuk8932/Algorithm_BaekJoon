package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14631번: 베시와 데이지
 *
 *	@see https://www.acmicpc.net/problem/14631/
 *
 */
public class Boj16431 {
	private static final String WIN_B = "bessie";
	private static final String WIN_D = "daisy";
	private static final String DRAW = "tie";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Point[] member = new Point[3];
		
		for(int i = 0; i < member.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			member[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		int b = getCost(member[0], member[2], 0);
		int d = getCost(member[1], member[2], 1);

		System.out.println(b == d ? DRAW : b < d ? WIN_B : WIN_D);	// 누가 더 빠른가?
	}
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static int getCost(Point start, Point end, int diff) {
		int cost = 0, row = 0, col = 0;
		row = Math.abs(start.row - end.row);
		col = Math.abs(start.col - end.col);
		
		if(diff == 0) cost = Math.max(row, col);		// 일반적인 거리
		else cost = row + col;							// 맨해튼 거리
		
		return cost;
	}
}

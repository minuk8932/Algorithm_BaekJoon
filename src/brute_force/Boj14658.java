package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14658번: 하늘에서 별똥별이 빗발친다.
 *
 *	@see https://www.acmicpc.net/problem/14658/
 *
 */
public class Boj14658 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Point[] coordinates = new Point[K];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			coordinates[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(K - getStars(N, M, L, coordinates));		// 막힌 별을 제외한 갯수
	}
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static int getStars(int n, int m, int l, Point[] p) {
		int max = 0;
		
		for(int left = 0; left < p.length; left++) {
			for(int right = 0; right < p.length; right++) {
				int count = 0;
				
				for(int target = 0; target < p.length; target++) {
					if(isContained(p[left], p[right], p[target], l)) {	// 임의의 위치 좌우 포인트를 사각화
						count++;					//  그 안에 존재하는 별의 갯수
					}
					
					if(count > max) max = count;
				}
			}
		}
		
		return max;
	}
	
	private static boolean isContained(Point left, Point right, Point target, int size) {
		if(left.row <= target.row && target.row <= left.row + size &&
			right.col <= target.col && target.col <= right.col + size) return true;
		
		return false;
	}
}

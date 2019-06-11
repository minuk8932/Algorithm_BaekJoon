package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14732번: 행사장 대여(small)
 *
 *	@see https://www.acmicpc.net/problem/14732/
 *
 */
public class Boj14732 {
	private static boolean[][] area = new boolean[501][501];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			covering(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(getArea());
	}
	
	private static void covering(int x1, int y1, int x2, int y2) {
		for(int row = x1; row < x2; row++) {
			for(int col = y1; col < y2; col++) {		// 원하는 행사장 만큼 배열 채우기
				if(area[row][col]) continue;
				area[row][col] = true;
			}
		}
	}
	
	private static int getArea() {
		int result = 0;
		
		for(int x = 0; x < area.length; x++) {
			for(int y = 0; y < area[x].length; y++) {
				if(area[x][y]) result++;				// 최종 행사장 크기
			}
		}
		
		return result;
	}
}

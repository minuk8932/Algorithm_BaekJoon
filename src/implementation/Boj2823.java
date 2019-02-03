package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2823번: 유턴 싫어
 *
 *	@see https://www.acmicpc.net/problem/2823/
 *
 */
public class Boj2823 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		boolean[][] map = new boolean[R + 2][C + 2];
		for(int i = 1; i < R + 1; i++) {
			String line = br.readLine();
			
			for(int j = 1; j < C + 1; j++) {
				if(line.charAt(j - 1) == '.') map[i][j] = true;
			}
		}
		
		System.out.println(search(R, C, map) ? 1 : 0);
	}
	
	private static boolean search(int r, int c, boolean[][] arr) {
		for(int i = 1; i < r + 1; i++) {
			for(int j = 1; j < c + 1; j++) {
				if(!arr[i][j]) continue;
				
				int[][] tmp = {{i, j + 1}, {i, j - 1}, {i - 1, j}, {i + 1, j}};
				int count = 0;
				
				for(int[] dir: tmp) {
					if(!arr[dir[0]][dir[1]]) count++;
				}
				
				if(count > 2) return true;		// 유턴해야하는 경우, 벽이 막혀있는 경우
			}
		}
		
		return false;		// 유턴 안해도되는 경우
	}
}

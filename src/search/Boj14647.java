package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14647번: 준오는 조류 혐오야!!
 *
 *	@see https://www.acmicpc.net/problem/14647/
 *
 */
public class Boj14647 {
	private static final char SHIT = '9';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		int total = 0;
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < m; j++) {
				char[] seq = st.nextToken().toCharArray();
				
				for(char c: seq) {
					if(c == SHIT) map[i][j]++;			// 비둘기 포착
				}
				
				total += map[i][j];
			}
		}		
		
		System.out.println(total - Math.max(search(n, m, map, true), search(m, n, map, false)));
	}
	
	private static int search(int n, int m, int[][] arr, boolean flag) {		// 박살낼 행이나 열을 결정한다.
		int max = 0;
		
		for(int i = 0; i < n; i++) {
			int value = 0;
			
			for(int j = 0; j < m; j++) {
				value += flag ? arr[i][j]: arr[j][i];
			}
			
			if(value > max) max = value;
		}
		
		return max;
	}
}

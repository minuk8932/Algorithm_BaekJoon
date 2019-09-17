package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 16970번: 정수 좌표의 개수
 */
public class Boj16970 {	
	private static boolean[][][][] used = new boolean[51][51][51][51];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		System.out.println(getPoints(N, M, K));
	}
	
	private static int getPoints(int n, int m, int k) {
		int count = 0;
		
		for(int x = 0; x <= n; x++) {									// 직선 사이에 존재하는 점의 갯수 찾기
			for(int y = 0; y <= m; y++) {
				count += isK(n, m, k, x, y);
			}
		}
		
		return count;
	}
	
	private static int isK(int n, int m, int k, int a, int b) {
		int result = 0;
		
		for(int x = 0; x <= n; x++) {
			for(int y = 0; y <= m; y++) {
				if(used[a][b][x][y]) continue;
				used[a][b][x][y] = used[x][y][a][b] = true;
				
				int value = gcd(Math.abs(a - x), Math.abs(b - y));		// 직선의 기울기
				if(value == k - 1) result++;
			}
		}
		
		return result;
	}
	
	private static int gcd(int a, int b) {
		if(b == 0) return a;
		return gcd(b, a % b);
	}
}

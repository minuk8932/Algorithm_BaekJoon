package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 14492번: 부울 행렬의 부울 곱
 *
 *	@see https://www.acmicpc.net/problem/14492/
 *
 */
public class Boj14492 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		boolean[][] A = new boolean[N][N];		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken()) == 1 ? true: false;
			}
		}
		
		boolean[][] B = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				B[i][j] = Integer.parseInt(st.nextToken()) == 1 ? true: false;
			}
		}
		
		System.out.println(boolMultiple(N, A, B));
	}
	
	private static int boolMultiple(int n, boolean[][] a, boolean[][] b) {
		int count = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				boolean flag = false;
				
				for(int k = 0; k < n; k++) {		// c[i][j] |= ΣiΣjΣk(a[i][k] & b[k][j])
					flag |= a[i][k] & b[k][j];
				}
				
				count += flag ? 1: 0;
			}
		}
		
		return count;
	}
}

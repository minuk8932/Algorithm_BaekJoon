package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14697번: 방 배정하기
 *
 *	@see https://www.acmicpc.net/problem/14697/
 *
 */
public class Boj14697 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		System.out.println(roomAllocation(A, B, C ,N));
	}
	
	private static int roomAllocation(int a, int b, int c, int n) {		
		for(int i = 0; i < n / a + 1; i++) {
			for(int j = 0; j < n / b + 1; j++) {
				for(int k = 0; k < n / c + 1; k++) {
					int success = (i * a) + (j * b) + (c * k);		// 모든 경우의 수 탐색
					
					if(success == n) return 1;
				}
			}
		}
		
		return 0;
	}
}

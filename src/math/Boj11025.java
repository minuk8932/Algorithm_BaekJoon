package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11025번: 조세퍼스 문제 3
 *
 *	@see https://www.acmicpc.net/problem/11025/
 *
 */
public class Boj11025 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		System.out.println(josephus(N, K));
	}
	
	private static int josephus(int n, int k) {
		int r = 0;
			
		for(int i = 1; i < n + 1; i++) {
			r = (r + k) % i;
			System.out.println(r);
		}
		
		return r + 1;
	}
}

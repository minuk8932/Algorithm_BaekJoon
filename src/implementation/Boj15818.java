package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15818번: 오버플로우와 모듈러
 *
 *	@see https://www.acmicpc.net/problem/15818/
 *
 */
public class Boj15818 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		long res = 1;
		st = new StringTokenizer(br.readLine());
		while(N-- > 0) {
			res = getModular(res, M, Integer.parseInt(st.nextToken()));		// 나머지 연산
		}
		
		System.out.println(res);
	}
	
	private static long getModular(long value, int mod, int input) {
		return ((value % mod) * (input % mod)) % mod;
	}
}

package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 17362번: 수학은 체육과목 입니다 2
 *
 *	@see https://www.acmicpc.net/problem/17362
 *
 */
public class Boj17362 {
	private static final int[] FINGER = {1, 2, 3, 4, 5};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		System.out.println(getFinger(n));
	}
	
	private static int getFinger(int N) {
		if(check(N, FINGER[2], 4)) return FINGER[2];
		if(check(N, FINGER[0], 8)) return FINGER[0];
		if(check(N, FINGER[4], 8)) return FINGER[4];
		
		if(check(N, FINGER[1], 8) || N % 8 == 0) return FINGER[1];
		return FINGER[3];
	}
	
	private static boolean check(int v, int f, int mod) {
		v -= f;
		if(v < 0) return false;
		
		return v % mod == 0 ? true: false;
	}
}

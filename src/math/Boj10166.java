package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 10166번: 관중석
 *
 *	@see https://www.acmicpc.net/problem/10166/
 *
 */
public class Boj10166 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] D = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		
		System.out.println(getSeats(D));
	}
	
	private static int gcd(int a, int b) {
		if(b == 0) return a;
		return gcd(b, a % b);
	}
	
	private static int getSeats(int[] range) {
		boolean[][] seats = new boolean[2_001][2_001];
		int count = 0;
		
		for(int t = range[0]; t <= range[1]; t++) {
			for(int i = 1; i <= t; i++) {
				int gcd = gcd(t, i);
				
				int m = t / gcd;				// make irreducible fraction
				int s = i / gcd;
				
				if(seats[s][m]) continue;		// check
				seats[s][m] = true;
				
				count++;						// remained seats
			}
		}
		
		return count;
	}
}

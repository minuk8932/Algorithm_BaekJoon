package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14490번: 백대열
 *
 *	@see https://www.acmicpc.net/problem/14490/
 *
 */
public class Boj14490 {
	private static final String COLON = ":";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), COLON);
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int s = gcd(n, m);
		System.out.println((n / s) + ":" + (m / s)); // 결과출력
	}
	
	private static int gcd (int N, int M) {		// 최대 공약수 메소드
		if(M == 0) return N;
		
		return gcd(M, N % M);
	}
}

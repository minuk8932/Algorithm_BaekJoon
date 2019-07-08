package math.modular_inverse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 14565번: 역원 구하기
 *
 *	@see https://www.acmicpc.net/problem/14565/
 *
 */
public class Boj14565 {
	private static class Pair{
		long gcd;
		long x;
		long y;
		
		public Pair(long gcd, long x, long y) {
			this.gcd = gcd;
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long N = Long.parseLong(st.nextToken());
		long A = Long.parseLong(st.nextToken());
		
		Pair res =  inverse(A, N);
		// 곱 역원에서 최대 공약수가 1이 아니면 역원이 없는 경우, 또한 역원이 음수면 N을 한번 더해 양수로 바꿔줌
		System.out.println(N - A + " " + (res.gcd != 1 ? -1: res.x < 0 ? N + res.x : res.x));
	}
	
	private static Pair inverse(long n, long a) {			// extended Euclidean Algorithm
		if(a == 0) return new Pair(n, 1, 0);
		
		Pair p = inverse(a, n % a);
		return new Pair(p.gcd, p.y, p.x - (n / a) * p.y);
	}
}

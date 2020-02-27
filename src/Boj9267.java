import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Boj9267 {
	private static final String Y = "YES";
	private static final String N = "NO";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		long S = Long.parseLong(st.nextToken());
		
		System.out.println(getResult(a, b, S));
	}

	private static long exEuclid(long b, long p) {
		long c = p;
		long d = b;
		long x = 0;
		long y = 1;

		while (d != 1) {
			long q = c / d;
			long e = c - d * q;
			long w = x - y * q;

			c = d;
			d = e;
			x = y;
			y = w;
		}

		if (y < 0)  y += p;
		return y;
	}


	private static String getResult(long a, long b, long sum) {
		if(a % sum == 0 || b % sum == 0 || (a + b) % sum == 0) return Y;

		long gcd = exEuclid(a, b);
		if(sum % gcd != 0) return N;

		return N;
	}
}

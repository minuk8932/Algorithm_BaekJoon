import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj4233 {
	private static final String NEW_LINE = "\n";
	private static final String Y = "yes";
	private static final String N = "no";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long p = Long.parseLong(st.nextToken());
			long a = Long.parseLong(st.nextToken());
			
			if(a == 0 && p == 0) break;
			sb.append(modularInverse(p, a) ? Y : N).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static boolean modularInverse(long p, long a) {
		long result = 1, tmp = a;
		long b = p - 2;
		
		while(b != 0) {
			if(b % 2 == 1) result = (result * a) % p;
			a = (a * a) % p;
			
			b >>= 1;
		}
		
		return ((result % p) * (tmp % p)) % p == 1 ? true : false;
	}
}

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj5615 {
	private static long[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 51, 53, 59, 61};
	private static final int INF = 10_000;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int count = 0;
		
		while(N-- > 0) {
			long sqr = Long.parseLong(br.readLine()) * 2 + 1;
			
			boolean flag = isPrime(sqr);
			count += flag ? 1: 0;
		}
		
		System.out.println(count);
	}
	
	private static boolean isPrime(long s) {
		if (s <= 1) return false;
		
	    if (s <= INF) {
	        for (long i = 2; i * i <= s; i++) {
	            if (s % i == 0) return false;
	        }
	        
	        return true;
	    }
	    
	    for (long p : prime) {
	        if (!mrJudge(s, p)) return false;
	    }

	    return true;
	}
	
	private static boolean mrJudge(long n, long a) {
		long d = n - 1;
		
	    while (d % 2 == 0) {
	        if (mod(a, d, n) == n - 1) return true;
	        d /= 2;
	    }
	    
	    long tmp = mod(a, d, n);
	    return tmp == n - 1 || tmp == 1;
	}
	
	private static long mod(long a, long d, long n) {
		a %= n;
	    long r = 1L;
	    
	    while (d > 0) {
	        if (d % 2 == 1) r = (r * a) % n;
	        a = (a * a) % n;
	        d /= 2;
	    }
	    
	    return r;
	}
}

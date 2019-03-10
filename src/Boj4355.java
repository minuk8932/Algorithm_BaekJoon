import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj4355 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = -1;
		
		while((n = Long.parseLong(br.readLine())) != 0) {
			getModularInverse(n);
		}
	}
	
	private static long getModularInverse(long a) {			// a ^ (p - 2) 구하기
		long result = 1;
		long p = 0, mod = 0;
		
		while(p != 0) {
			if(p % 2 == 1) result = (result * a) % mod;
			a = (a * a) % mod;
			
			p >>= 1;
		}
		
		return result;
	}
}

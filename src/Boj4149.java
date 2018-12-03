import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj4149 {
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		
		System.out.println(getPrimeFactor(N));
	}
	
	private static StringBuilder getPrimeFactor(long n) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 2; i * i < n + 1; i++) {
			while (n % i == 0) {
				n /= i;
				sb.append(i).append(NEW_LINE);
			}
		}
		
		if(n != 1) sb.append(n).append(NEW_LINE);
		
		return sb;
	}
}

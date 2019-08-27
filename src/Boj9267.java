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
	
	private static String getResult(long a, long b, long sum) {
		if(a == sum || b == sum) return Y;
		if(a + b == sum) return Y;
		
		if(a == 0 && b == 0 && sum != 0) return N;
		if(a > sum || b > sum) return N;
		
		if(a == 0) {
			if(sum % b == 0) return Y;
			else return N;
		}
		
		if(b == 0) {
			if(sum % a == 0) return Y;
			else return N;
		}
		
		

		return Y;
	}
}

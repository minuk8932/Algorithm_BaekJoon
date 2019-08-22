import java.io.BufferedReader;
import java.io.InputStreamReader;
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
		
		long tmp = a + b;
		if(tmp == 0 && sum > 0) return N;
		
		long val = sum / tmp;
		if(val == 0) return N;
		
		long mod = sum % tmp;
		if(sum != 0 && mod == 0) return Y;
		System.out.println(72889670276447310L % tmp);
		if(a != 0 && b != 0) {
			if(mod % a == 0 || mod % b == 0) return Y;
		}
		
		return N;
	}
}

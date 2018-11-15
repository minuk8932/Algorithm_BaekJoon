import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1494 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long first = Long.parseLong(st.nextToken());
		long second = Long.parseLong(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		
		while(N-- > 0) {
			sb.append(Process(first, second, Long.parseLong(br.readLine()))).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static long Process(long f, long s, long idx) {
		long max = Math.max(f, s);
		long min = Math.min(f, s);
		
		if(idx == 0) return f;
		if(idx == 1) return s;
		
		long limit = max / min + 1;
		long mod = max % min;
		
		long res = 0;
		
		if(idx < limit) {
			res = Math.abs(max - (min * idx));
		}
		else {
			if((limit - idx) % 2 == 0) {
				res = Math.abs(min - mod);
			}
			else {
				res = mod;
			}
		}
		
		return res;
	}
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1934 {
	private static int res = 1;
	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			sb.append(lcm(A, B)).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static int lcm(int num1, int num2) {		
		if(num2 == 0) {
			return res;
		}
		
		res *= num2;
		return lcm(num2, num1 % num2);
	}
}

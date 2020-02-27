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
			long input = Long.parseLong(br.readLine());
			sb.append(recursion()).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}

	private static long recursion(){
		return 0;
	}
}

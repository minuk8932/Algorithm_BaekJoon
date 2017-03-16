package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11006 {
	public static final String NEW_LINE = "\n";
	public static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int legs = (M * 2) - N;
			int saved = M - legs;
			
			sb.append(legs).append(SPACE).append(saved).append(NEW_LINE);
		}
		System.out.println(sb.toString());
	}

}

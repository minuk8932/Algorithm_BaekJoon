package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11659 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] sum = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N + 1; i++) {
			sum[i] += (sum[i - 1] + Integer.parseInt(st.nextToken()));
		}
		
		StringBuilder sb = new StringBuilder();
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken());
			
			sb.append(sum[to] - sum[from]).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
}

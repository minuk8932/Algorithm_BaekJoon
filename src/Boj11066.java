import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11066 {
	private static long[][] dp;
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] files = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				files[i] = Integer.parseInt(st.nextToken());
			}
			
			dp = new long[N][N];
			sb.append(merge(N, files)).append(NEW_LINE);			// 인접끼리만 합침
		}
		
		System.out.println(sb.toString());
	}
	
	private static int merge(int n, int[] arr) {

		int sum = 0;
		
		return sum;
	}
}

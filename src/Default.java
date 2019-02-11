import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Default {
	private static final int INF = 5_000_000;
	
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		int N = Integer.parseInt(st.nextToken());
//		int K = Integer.parseInt(st.nextToken());
//		
//		int[] A = new int[N + 1];
//		A[0] = Integer.MIN_VALUE;
//		st = new StringTokenizer(br.readLine());
//		for (int i = 1; i <= N; i++) {
//			A[i] = Integer.parseInt(st.nextToken());
//		}
//		Arrays.sort(A);
//		System.out.println(A[K]);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= INF; i++) {
			sb.append(-i).append(" ");
		}
		
		System.out.println(sb);
	}
}
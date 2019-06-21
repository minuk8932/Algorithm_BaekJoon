import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10986 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] A = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N + 1; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(countSection(N, M, A));
	}
	
	private static int countSection(int n, int m, int[] A) {
		int count = 0;
		
		long[][] sum = new long[2][n + 2];
		for(int i = 1; i < n + 1; i++) {
			sum[0][i] += sum[0][i - 1] + A[i];
		}
		
		for(int i = n; i > 0; i--) {
			sum[1][i] += sum[1][i + 1] + A[i];
		}
		
		
		
		return count;
	}
}

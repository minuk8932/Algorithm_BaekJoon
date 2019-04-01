import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1215 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		System.out.println(n == 1 ? 0 : josephs(n, k));
	}
	
	private static long josephs(long N, long K) {
		long res = K * (N - K); 			// K ~ N 사이 나머지
		res += ((K / 2) + 1 + 1) * (K / 2) / 2;		// K/2 + 1 ~ K 사이 나머지 총 합
		
		return res;
	}
}

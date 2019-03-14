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
		long res = N > K ? (N - K) * K : 0;
		long half = K / 2;
		if(K % 2 == 0) half--;
		
		res += half < N ? (1 + half) * half / 2 : 0;
		long loop = K / 2 > N ? N : K / 2;
		
		for(long i = 1; i <= loop; i++) {			// 개선해야함
			res += (K % i);
		}
		
		return res;
	}
}

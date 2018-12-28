import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1215 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		System.out.println(josephsSequence(1, k, n, k));
	}
	
	private static long josephsSequence(int i, int r, int N, int K) {
		if (i > N) {
			return 0;
		}
		else {
			if(i > K) return K;
			else if(i == K) return 0;
			else return josephsSequence(i * 2, r % i, N, K) + josephsSequence(i * 2 + 1, r % i, N, K) + K % i;
		}
	}
}

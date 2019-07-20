import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14222 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(maker(N, K, arr));
	}
	
	private static int maker(int n, int k, int[] arr) {
		boolean[] key = new boolean[n + 1];
		
		for(int i = 0; i < n; i++) {
			int src = arr[i];
			
			while(src < n + 1) {
				key[src] = true;
				src += k;
			}
		}
		
		for(int i = 1; i < n + 1; i++) {
			if(!key[i]) return 0;
		}
		
		return 1;
	}
}

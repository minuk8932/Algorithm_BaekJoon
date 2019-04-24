package sliding_window;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10025번: 게으른 백곰
 *
 *	@see https://www.acmicpc.net/problem/10025/
 *
 */
public class Boj10025 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] ice = new int[1_000_001];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int gi = Integer.parseInt(st.nextToken());
			int xi = Integer.parseInt(st.nextToken());
			
			ice[xi] = gi;			// 좌표에 양동이
		}
		
		System.out.println(maxIce(N, K, ice));
	}
	
	private static int maxIce(int n, int k, int[] arr) {
		int max = 0, tmp = 0;
		k = k * 2 + 1;									// 양방향 범위
		
		for(int i = 0; i < arr.length; i++) {			// sliding window
			if(i >= k) tmp -= arr[i - k];
			tmp += arr[i];
			
			if(tmp > max) max = tmp;
		}
		
		return max;
	}
}

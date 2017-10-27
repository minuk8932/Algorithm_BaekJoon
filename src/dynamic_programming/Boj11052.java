package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 		@author minchoba 
 * 		Baekjun : 11052 붕어빵 판매하기
 */
public class Boj11052 {
	private static final String SPACE = " ";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] fish = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		for (int i = 1; i < N + 1; i++) {
			fish[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		int[] dp = new int[N + 1]; // [fish size]

		for(int i = 1; i <= N ; i++){
			for(int j = 1; j <= N; j++){
				if(j >= i){
					dp[j] = Math.max(dp[j], dp[j-i] + fish[i]);
				}
			}
		}
		System.out.println(dp[N]);
	}
}

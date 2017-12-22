package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj11053 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		for(int i = N; i > 0; i--){
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N + 1];
		int res = 0;
		
		Arrays.fill(dp, 1);
		
		for(int i = 1; i < N + 1; i++){
			for(int j = 1; j < i + 1; j++){
				if(nums[j] > nums[i]){
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		for(int i = 1; i < N + 1; i++){
			res = Math.max(dp[i], res);
		}
		
		System.out.println(res);
	}
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11053 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		for(int i = 1; i < N + 1; i++){
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N + 1];
		
		for(int i = 1; i < N + 1; i++){
			for(int j = 1; j < N; j++){
				if(nums[i] < nums[j - 1]){
					dp[i]++;
				}
			}
		}
		int res = 0;
		
		for(int i = 1; i < N + 1; i++){
			if(N == 1){
				res = 1;
			}
			else{
				res = Math.max(res, dp[i]);
			}
		}
		System.out.println(res);
	}
}

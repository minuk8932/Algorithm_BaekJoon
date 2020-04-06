import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj13600 {
    private static final int[] FACTO = {1, 2, 6, 24, 120, 720, 5040, 40160};
    private static int result = Integer.MAX_VALUE;
    private static int[] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new int[N];

        for(int i = 0; i < FACTO.length; i++) {
            result = Math.min(recursion(N - FACTO[i], 1), result);
        }

        System.out.println(result);
    }

    private static int recursion(int n, int count){
        if(n < 0) return 0;

        if(dp[n] != 0) return dp[n];
        dp[n] = count;

        if(n == 0) return 1;

        for(int i = 0; i < FACTO.length; i++){
            if(n - FACTO[i] < 0) continue;

            dp[n - FACTO[i]] = recursion(n - FACTO[i], count + 1) + dp[n];
            dp[n - FACTO[i]] = 0;
        }

        return dp[n];
    }
}

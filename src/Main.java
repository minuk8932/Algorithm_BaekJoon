import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static final int MOD = 15_746;
    private static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];
        Arrays.fill(dp, -1);
        System.out.println(recursion(N));
    }

    private static int recursion(int n) {
        if(n == 0) return 1;
        if(dp[n] != -1) return dp[n];

        int result = recursion(n - 1);

        if(n >= 2) result = modulation(result, recursion(n - 2));
        return dp[n] = result;
    }

    private static int modulation(int a, int b) {
        return ((a % MOD) + (b % MOD)) % MOD;
    }
}

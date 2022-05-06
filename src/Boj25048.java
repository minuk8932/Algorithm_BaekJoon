import common.Node;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj25048 {

    private static final long INF = 1_000_000_000_000_000L;
    private static Node<Integer, Long>[] switches;
    private static long[][][] dp;

    private static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        switches = new Node[N + 1];
        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            long b = Long.parseLong(st.nextToken());

            switches[i] = new Node.Builder(a).cost(b).build();
        }

        M = Integer.parseInt(br.readLine());
        System.out.println(LAN());
    }

    private static long LAN() {
        if(M == 1) return 0;

        dp = new long[2][N + 1][M + 1];
        for(int i = 0; i <= N; i++) {
            Arrays.fill(dp[0][i], -1);
            Arrays.fill(dp[1][i], -1);
        }

        long answer = recursion(N, M, 0);
        return answer == INF ? -1: answer;
    }

    private static long recursion(int current, int computer, int lan) {
        if(computer <= 0) return 0;
        if(current == 0) return INF;

        if(dp[lan][current][computer] != -1) return dp[lan][current][computer];
        long answer = recursion(current - 1, computer, lan);

        answer = Math.min(answer, recursion(current - 1
                , computer - switches[current].getNode() + lan, lan | 1)
            + switches[current].getCost());

        return dp[lan][current][computer] = answer;
    }
}

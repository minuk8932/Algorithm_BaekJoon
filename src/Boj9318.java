import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj9318 {

    private static final String NEW_LINE = "\n";
    private static final int INF = 10_000_000;

    private static int[] tree;
    private static int[] lazy;

    private static int S = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            init(n);


        }

        System.out.println(sb);
    }

    private static void init(int n) {
        while(S <= n) {
            S <<= 1;
        }

        int shift = S << 1;
        tree = new int[shift];
        lazy = new int[shift];
    }
}

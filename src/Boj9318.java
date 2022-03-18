import common.Pair;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj9318 {

    private static int start = 1 << 20;
    private static int[] tree;
    private static int[] lazy;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            init();


        }

        System.out.println(sb.toString());
    }

    private static void init() {
        int size = start << 1;

        tree = new int[size];
        lazy = new int[size];
    }
}

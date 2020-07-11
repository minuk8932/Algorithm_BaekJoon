package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author exponential-e
 * 백준 10888번: 두 섬간의 이동
 *
 * @see https://www.acmicpc.net/problem/10888/
 *
 */
public class Boj10888 {
    private static int[] parent;

    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        init(N);

        ArrayList<Integer> input = new ArrayList<>();
        int loop = N - 1;
        while(loop-- > 0) {
            input.add(Integer.parseInt(br.readLine()) - 1);
        }

        System.out.println(bridge(input));
    }

    private static String bridge(ArrayList<Integer> make) {
        StringBuilder sb = new StringBuilder();
        long pairs = 0;
        long bridges = 0;

        for(int m: make) {
            int x = find(m);
            int y = find(m + 1);

            int[] size = {-parent[x], -parent[y]};

            pairs -= combination(size[0], true) + combination(size[1], true);
            bridges -= combination(size[0] - 1, false) + combination(size[1] - 1, false);       // except redundant

            merge(x, y);

            int total = size[0] + size[1];
            pairs += combination(total, true);
            bridges += combination(total - 1, false);                               // pure accumulation

            sb.append(pairs).append(SPACE).append(bridges).append(NEW_LINE);
        }

        return sb.toString();
    }

    private static long combination(long n, boolean flag) {
        if(flag) return n * (n - 1) / 2L;
        else return n * (n + 1) * (n + 2) / 6L;
    }

    private static void init(int n) {
        parent = new int[n];

        for(int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    private static int find(int x){
        if(parent[x] < 0) return x;
        else return parent[x] = find(parent[x]);
    }

    private static void merge(int x, int y) {
        if(x == y) return;

        if(parent[x] < parent[y]){
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }
    }
}

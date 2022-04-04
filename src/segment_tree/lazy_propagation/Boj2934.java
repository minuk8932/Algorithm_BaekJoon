package segment_tree.lazy_propagation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2934번: LRH 식물
 *
 * @see https://www.acmicpc.net/problem/2934
 *
 */
public class Boj2934 {

    private static final int SIZE = 100_001;
    private static final String NEW_LINE = "\n";

    private static int[] tree = new int[SIZE];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int sumA = counting(a);
            int sumB = counting(b);

            sb.append(sumA + sumB).append(NEW_LINE);

            planting(a, -sumA);
            planting(a + 1, sumA);
            planting(b, -sumB);
            planting(b + 1, sumB);

            planting(a + 1, 1);
            planting(b, -1);
        }

        System.out.println(sb.toString());
    }

    private static int counting(int node) {
        int ans = 0;

        while (node > 0) {
            ans += tree[node];
            node -= (node & -node);
        }

        return ans;
    }

    private static void planting(int node, int difference) {
        while (node < SIZE) {
            tree[node] += difference;
            node += (node & -node);
        }
    }
}

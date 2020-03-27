package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 3830번: 교수님은 기다리지 않는다.
 *
 * @see https://www.acmicpc.net/problem/3830/
 *
 */
public class Boj3830 {
    private static int[] parent;
    private static long[] weight;

    private static final String NEW_LINE = "\n";
    private static final String U = "UNKNOWN";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if (N + M == 0) break;

            parent = new int[N];
            weight = new long[N];
            Arrays.fill(parent, -1);

            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;

                if (cmd.equals("!")) {
                    long w = Long.parseLong(st.nextToken());
                    merge(a, b, w);
                } else {
                    sb.append(find(a) != find(b) ? U : weight[b] - weight[a]).append(NEW_LINE);
                }
            }
        }

        System.out.println(sb.toString());
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
        int p = find(parent[x]);
        weight[x] += weight[parent[x]];                     // value update

        return parent[x] = p;
    }

    private static void merge(int x, int y, long w) {
        int[] prev = {x , y};
        x = find(x);
        y = find(y);

        if(x == y) return;
        long cal = w - weight[prev[1]] + weight[prev[0]];   // difference

        if(parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
            weight[y] += cal;
        }
        else{
            parent[y] += parent[x];
            parent[x] = y;
            weight[x] -= cal;
        }
    }
}

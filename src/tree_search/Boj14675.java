package tree_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14675번: 단절점과 단절선
 *
 * @see https://www.acmicpc.net/problem/14675/
 *
 */
public class Boj14675 {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] tree = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        int loop = N - 1;
        while(loop-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        StringBuilder sb = new StringBuilder();
        int q = Integer.parseInt(br.readLine());

        while(q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken()) - 1;

            sb.append(t == 2 ? "yes": tree[k].size() == 1 ? "no": "yes").append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }
}

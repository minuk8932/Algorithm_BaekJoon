package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20931번: 혹 떼러 갔다 혹 붙여 온다
 *
 * @see https://www.acmicpc.net/problem/20931
 *
 */
public class Boj20931 {

    private static final String ADD = "ad-hoc";
    private static final String NEW_LINE = "\n";

    private static int[][] parent = new int[200_000][21];
    private static int[] depth = new int[200_000];
    private static long[][] cost = new long[200_000][21];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Q = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        int last = 0;
        int index = 1;

        while(Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int hoc = Integer.parseInt(st.nextToken());
            long L = Long.parseLong(st.nextToken());

            if(ADD.equals(cmd)) update(last, hoc, index++, L);
            else sb.append(last = search(hoc, L)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * LCA partition
     *
     * check jump index & length
     *
     * @param target
     * @param length
     * @return
     */
    private static int search(int target, long length) {
        for(int i = 20; i >= 0; i--){
            long jump = 1L << i;

            if(jump > depth[target] || length < cost[target][i]) continue;
            length -= cost[target][i];
            target = parent[target][i];
        }

        return target;
    }

    /**
     *
     * update parent idx & each cost by online
     *
     * @param last
     * @param hoc
     * @param index
     * @param L
     */
    private static void update(int last, int hoc, int index, long L){
        int prev = (last + hoc) % index;

        depth[index] = depth[prev] + 1;
        parent[index][0] = prev;
        cost[index][0] = L;

        for(int p = 1; p < 21; p++){
            parent[index][p] = parent[parent[index][p - 1]][p - 1];
            cost[index][p] = cost[index][p - 1] + cost[parent[index][p - 1]][p - 1];
        }
    }
}

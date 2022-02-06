package segment_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2042번: 구간 합 구하기
 *
 * @see https://www.acmicpc.net/problem/2042
 *
 */
public class Boj2042 {
    private static final String NEW_LINE = "\n";

    private static int S = 1;

    private static long[] tree;
    private static long[] data;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        init(N);
        for(int i = 0; i < N; i++) {
            data[i] = Long.parseLong(br.readLine());
        }

        distribution(1, S, 1);

        StringBuilder sb = new StringBuilder();
        int loop = M + K;

        while(loop-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a == 1){
                long diff = c - tree[(b + S - 1)];
                update(1, S, 1, b, diff);
            }
            else{
                sb.append(getSectionSum(1, b, (int) c, 1, S - 1)).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }

    private static void distribution(int left, int right, int node) {
        int diff = node - S;

        if(left == right){
            if(diff < data.length)  tree[node] = data[diff];
            return;
        }

        if(left > right) return;
        int mid = (left + right) >> 1;
        int next = node << 1;

        distribution(left, mid, next);
        distribution(mid + 1, right, next + 1);

        tree[node] = tree[next] + tree[next + 1];
    }

    private static void init(int n) {
        while(S <= n) {
            S <<= 1;
        }

        tree = new long[(S << 1) + 1];
        data = new long[n];
    }

    private static void update(int left, int right, int node, int target, long diff) {
        if(target < left || target > right) return;

        tree[node] += diff;
        if(left == right) return;

        int mid = (left+right) >> 1;
        int next = node << 1;

        update(left, mid, next, target, diff);
        update(mid + 1, right, next + 1, target, diff);
    }

    private static long getSectionSum(int start, int from, int to, int l, int r) {
        if(r < from || l > to) return 0;
        if(from <= l && to >= r) return tree[start];

        int mid = (l + r) >> 1;
        int next = start << 1;

        return getSectionSum(next, from, to, l, mid) + getSectionSum(next + 1, from, to, mid + 1, r);
    }
}

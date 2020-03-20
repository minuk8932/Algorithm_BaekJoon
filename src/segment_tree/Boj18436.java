package segment_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18436번: 수열과 쿼리 37
 *
 * @see https://www.acmicpc.net/problem/18436/
 *
 */
public class Boj18436 {
    private static int[] tree;
    private static int N, S = 1;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = S; i < S + N; i++){
            int num = Integer.parseInt(st.nextToken());
            tree[i] = num % 2;      // odd count
        }

        for(int i = S - 1; i > 0; i--) {
            int[] son = makeSon(i);
            tree[i] = tree[son[0]] + tree[son[1]];
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;

            if(cmd == 1){
                int x = r + 1;
                tree[S + l] = x % 2;
                update(l, l, x % 2, 1, 0, S - 1);
            }
            else if(cmd == 2){
                sb.append(r - l + 1 - sum(l, r, 1, 0, S - 1)).append(NEW_LINE);     // get even count
            }
            else{
                sb.append(sum(l, r, 1, 0, S - 1)).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }

    private static void init(){
        while(S <= N) S <<= 1;
        tree = new int[S * 2];
    }

    private static int[] makeSon(int node){
        int son = node * 2;
        return new int[]{son, ++son};
    }

    private static void update(int left, int right, int val, int node, int start, int end){
        if(right < start || end < left) return;
        if(left <= start && end <= right){
            tree[node] = val;
            return;
        }

        int[] son = makeSon(node);
        int mid = (start + end) / 2;

        update(left, right, val, son[0], start, mid);
        update(left, right, val, son[1], mid + 1, end);

        tree[node] = tree[son[0]] + tree[son[1]];
    }

    private static int sum(int left, int right, int node, int start, int end){
        if(right < start || end < left) return 0;
        if(left <= start && end <= right) return tree[node];

        int[] son = makeSon(node);
        int mid = (start + end) / 2;

        return sum(left, right, son[0], start, mid) + sum(left, right, son[1], mid + 1, end);
    }
}

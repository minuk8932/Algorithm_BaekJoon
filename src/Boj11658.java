import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11658 {
    private static long[] tree;
    private static long[] lazy;

    private static int N, N2, S = 1;
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        N2 = N * N;

        init();

        int[] arr = new int[N2];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                arr[i * N + j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = S; i < N2 + S; i++){
            tree[i] = arr[i - S];
        }

        for(int i = S - 1; i > 0; i--){
            int[] son = makeSon(i);
            tree[i] = tree[son[0]] + tree[son[1]];
        }

        StringBuilder sb = new StringBuilder();

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            int src = x * N + y;

            if(w == 0){
                int c = Integer.parseInt(st.nextToken());
                update(src, src, c, 1, 0, S - 1);
            }
            else{
                int x1 = Integer.parseInt(st.nextToken()) - 1;
                int y1 = Integer.parseInt(st.nextToken()) - 1;

                long result = 0;

                for(int row = x; row <= x1; row++) {
                    int start = row * N;

                    src = start + y;
                    int snk = start + y1;

                    result += sum(src, snk, 1, start, start + N - 1);
                }

                sb.append(result).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }

    private static void init(){
        while(S < N2) S <<= 1;

        tree = new long[S * 2];
        lazy = new long[S * 2];
    }

    private static int[] makeSon(int node){
        int son = node * 2;
        return new int[]{son, ++son};
    }

    private static void propagation(int node, int start, int end){
        if(lazy[node] == 0) return;

        if(start != end){
            int[] son = makeSon(node);                      // push lazy

            lazy[son[0]] += lazy[node];
            lazy[son[1]] += lazy[node];
        }

        tree[node] += lazy[node] * (end - start + 1);       // add values
        lazy[node] = 0;
    }

    private static void update(int left, int right, int val, int node, int start, int end){
//        propagation(node, start, end);

        if(right < start || end < left) return;
        if(left <= start && end <= right){
//            lazy[node] += val;
//            propagation(node, start, end);
            tree[node] = val;

            return;
        }

        int[] son = makeSon(node);
        int mid = (start + end) / 2;

        update(left, right, val, son[0], start, mid);
        update(left, right, val, son[1], mid + 1, end);

        tree[node] = tree[son[0]] + tree[son[1]];               // update
    }

    private static long sum(int left, int right, int node, int start, int end){
//        propagation(node, start, end);

        if(right < start || end < left) return 0;
        if(left <= start && end <= right) return tree[node];

        int[] son = makeSon(node);
        int mid = (start + end) / 2;

        return sum(left, right, son[0], start, mid) + sum(left, right, son[1], mid + 1, end);       // make section sum
    }
}

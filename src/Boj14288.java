import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj14288 {
    private static ArrayList<Integer>[] link;
    private static int[] tree;
    private static int[] lazy;

    private static int[] start, end;
    private static int N, S = 1;
    private static int count = -1;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init();

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            int val = Integer.parseInt(st.nextToken());
            if(val == -1) continue;

            link[val].add(i);
        }

        dfs(1);

        StringBuilder sb = new StringBuilder();
        boolean flag = false;

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            if(cmd == 3){
                flag = !flag;
                continue;
            }

            int i = Integer.parseInt(st.nextToken());

            if(cmd == 1){
                int w = Integer.parseInt(st.nextToken());
                add(start[i], flag ? start[i] : end[i], w, 1, 0, N - 1);
            }
            else {
                sb.append(sum(start[i], flag ? end[i]: start[i], 1, 0, N - 1)).append(NEW_LINE);
            }
        }

//        for(int i = 0; i < tree.length; i++){
//            System.out.print(tree[i] + " ");
//        }
//        System.out.println();

        System.out.println(sb.toString());
    }

    private static void init(){
        while(S <= N) S <<= 1;

        tree = new int[S * 2];
        lazy = new int[S * 2];
        start = new int[N + 1];
        end = new int[N + 1];

        link = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++){
            link[i] = new ArrayList<>();
        }
    }

    private static void dfs(int current){
        start[current] = ++count;

        for(int next: link[current]){
            dfs(next);
        }

        end[current] = count;
    }

    private static int[] makeSon(int node){
        int son = node * 2;
        return new int[]{son, ++son};
    }

    private static void propagation(int node, int start, int end){
        if(lazy[node] == 0) return;

        if(start != end){
            int[] son = makeSon(node);

            lazy[son[0]] += lazy[node];
            lazy[son[1]] += lazy[node];
        }

        tree[node] += lazy[node] * (end - start + 1);
        lazy[node] = 0;
    }

    private static void add(int left, int right, int val, int node, int start, int end){
        propagation(node, start, end);

        if(right < start || end < left) return;
        if(left <= start && end <= right) {
            lazy[node] += val;
            propagation(node, start, end);

            return;
        }

        int[] son = makeSon(node);
        int mid = (start + end) / 2;

        add(left, right, val, son[0], start, mid);
        add(left, right, val, son[1], mid + 1, end);

        tree[node] = tree[son[0]] + tree[son[1]];
    }

    private static int sum(int left, int right, int node, int start, int end){
        propagation(node, start, end);

        if(right < start || end < left) return 0;
        if(left <= start && end <= right) return tree[node];

        int[] son = makeSon(node);
        int mid = (start + end) / 2;

        return sum(left, right, son[0], start, mid) + sum(left, right, son[1], mid + 1, end);
    }
}

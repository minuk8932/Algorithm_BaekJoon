import common.Query;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj16978 {


    private static final List<Query<Integer, Long>> UPDATE_QUERY = new ArrayList<>();
    private static final PriorityQueue<Query<Integer, Long>> PRINT_QUERY = new PriorityQueue<>(
        Comparator.comparingInt(Query<Integer, Long>::getIndex));
    private static final String NEW_LINE = "\n";

    private static long[] result;
    private static long[] tree;
    private static long[] lazy;

    private static int size = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        init(N);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = size; i < size + N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = size - 1; i > 0; i--) {
            int[] son = getSon(i);
            tree[i] = tree[son[0]] + tree[son[1]];
        }

        int M = Integer.parseInt(br.readLine());
        int index = 0;

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            if(cmd == 1) {
                int i = Integer.parseInt(st.nextToken());
                long v = Long.parseLong(st.nextToken());

                UPDATE_QUERY.add(new Query.Builder(i).cost(v).build());
            }
            else {
                int k = Integer.parseInt(st.nextToken());
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());

                PRINT_QUERY.offer(new Query.Builder(i).to(j).index(k).sequence(index++).build());
            }
        }

        queryProcess();

        StringBuilder sb = new StringBuilder();
        for(long res: result) {
            sb.append(res).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void queryProcess() {
        result = new long[PRINT_QUERY.size()];

        Query<Integer, Long> current = PRINT_QUERY.peek();

        while(current.getIndex() == 0) {
            current = PRINT_QUERY.poll();

            result[current.getSequence()] = add(current.getFrom(), current.getTo(),1
                , 1, size);
        }

        int len = UPDATE_QUERY.size();
        for(int seq = 1; seq < len; seq++) {

            Query<Integer, Long> updateCurrent = UPDATE_QUERY.get(seq);
            update(updateCurrent.getFrom(), updateCurrent.getFrom(), 1
                , updateCurrent.getCost(), 1, size);

            while(current.getIndex() == seq) {
                current = PRINT_QUERY.poll();

                result[current.getSequence()] = add(current.getFrom(), current.getTo(),1
                    , 1, size);
            }
        }
    }

    private static long add(int left, int right, int node, int currentStart, int currentEnd) {
        propagation(node, currentStart, currentEnd);

        if(left > currentEnd || currentStart > right) return 0;
        if(left <= currentStart && currentEnd <= right) return tree[node];

        int mid = (currentEnd + currentStart) >> 1;
        int[] son = getSon(node);

        return add(left, right, son[0], currentStart, mid) |
            add(left, right, son[1], mid + 1, currentEnd);
    }

    private static void update(int left, int right, int node
        , long value, int currentStart, int currentEnd) {

        propagation(node, currentStart, currentEnd);

        if(left > currentEnd || currentStart > right) return;
        if(left <= currentStart && currentEnd <= right) {
            lazy[node] = value;
            propagation(node, currentStart, currentEnd);
            return;
        }

        int mid = (currentEnd + currentStart) >> 1;
        int[] son = getSon(node);

        update(left, right, son[0], value, currentStart, mid);
        update(left, right, son[1], value, mid + 1, currentEnd);
        tree[node] = tree[son[0]] + tree[son[1]];
    }

    private static void propagation(int node, int currentStart, int currentEnd) {
        if (lazy[node] == 0) return;

        if(currentStart != currentEnd) {
            int[] son = getSon(node);
            lazy[son[0]] = lazy[node];
            lazy[son[1]] = lazy[node];
        }

        tree[node] = lazy[node] * (currentEnd - currentStart + 1);
        lazy[node] = 0;
    }

    private static int[] getSon(int node) {
        int son = node << 1;
        return new int[]{son, ++son};
    }

    private static void init(int n) {
        while(size <= n) {
            size <<= 1;
        }

        tree = new long[size << 1];
        lazy = new long[size << 1];
    }
}

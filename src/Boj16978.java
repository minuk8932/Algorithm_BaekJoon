import common.Query;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj16978 {


    private static final List<Query<Integer, Long>> UPDATE_QUERY = new ArrayList<>();
    private static final List<Query<Integer, Long>> PRINT_QUERY = new ArrayList<>();

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

        int M = Integer.parseInt(br.readLine());
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            if(cmd == 1) {
                int i = Integer.parseInt(st.nextToken());
                long v = Long.parseLong(st.nextToken());
                UPDATE_QUERY.add(new Query.Builder(i).value(v).build());
            }
            else {
                int k = Integer.parseInt(st.nextToken());
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                PRINT_QUERY.add(new Query.Builder());
            }
        }
    }

    private static void init(int n) {
        while(size <= n) {
            size <<= 1;
        }

        tree = new long[size << 1];
        lazy = new long[size << 1];
    }
}

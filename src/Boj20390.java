import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj20390 {
    private static long A, B, C, D;
    private static final long LIMIT = 10_000_000_000L;

    private static int[] parent;

    private static class Data {
        int index;
        long v1;
        long v2;

        public Data(int index, long v1, long v2) {
            this.index = index;
            this.v1 = v1;
            this.v2 = v2;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());
        D = Long.parseLong(st.nextToken());

        long[] X = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            X[i] = Long.parseLong(st.nextToken()) % C;
        }

        A %= C;
        B %= C;

        init(X);
        System.out.println();
    }

    private static void init(long[] x) {
        ArrayList<Data> pairs = new ArrayList<>();

        for(int i = 0; i < x.length; i++) {
            Data data = new Data(-1, x[i], Long.MAX_VALUE);

            for(int j = i + 1; j < x.length; j++) {
                if(find(i) == find(j)) continue;
                if(data.v2 < x[j]) continue;

                data.index = j;
                data.v2 = x[j];
            }

            merge(i, data.index);
            pairs.add(data);
        }
    }

    private static int find(int x) {
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static void merge(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return;

        if (parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }
    }
}

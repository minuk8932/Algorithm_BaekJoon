import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj16965 {
    private static final String NEW_LINE = "\n";

    private static ArrayList<Integer>[] graph;
    private static boolean[] visit;
    private static int[] parent;

    private static class Pair {
        int idx;
        int from;
        int to;

        public Pair(int idx, int from, int to) {
            this.idx = idx;
            this.from = from;
            this.to = to;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        parent = new int[N];
        Arrays.fill(parent, -1);

        visit = new boolean[N];
        graph = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        StringBuilder sb = new StringBuilder();
        ArrayList<Pair> section = new ArrayList<>();

        int count = 0;

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            if (cmd == 1) {
                section.add(new Pair(count++, f, t));
                makeSection(section);
            }
            else {
                sb.append(find(t - 1) == find(f - 1) ? 1: 0).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }

    private static void makeSection(ArrayList<Pair> sec) {
        for (Pair current: sec) {
            for (Pair another: sec) {
                if ((current.from < another.from && another.from < current.to)
                        || (current.from < another.to && another.to < current.to)) {

                    merge(another.idx, current.idx);
                }
            }
        }
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
        else return parent[x] = find(parent[x]);
    }

    private static void merge(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return;

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

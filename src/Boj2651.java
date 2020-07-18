import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj2651 {
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";
    private static final long INF = 1_000_000_000_000L;

    private static ArrayList<Node>[] path;
    private static long[] prefix;
    private static int N;

    private static class Node implements Comparable<Node>{
        int node;
        long cost;

        public Node(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost < n.cost ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int threshold = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());

        path = new ArrayList[N + 1];
        prefix = new long[N + 2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N + 1; i++) {
            long cost = Long.parseLong(st.nextToken());
            path[i].add(new Node(i + 1, cost));
            prefix[i + 1] = prefix[i] + cost;
        }

        int[] vertex = new int[N + 2];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            vertex[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(racing(vertex));
    }


    private static String racing(int[] v) {



        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }
}

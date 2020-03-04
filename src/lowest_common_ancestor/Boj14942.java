package lowest_common_ancestor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 *
 * @author exponential-e
 * 백준 14942번: 개미
 *
 * @see https://www.acmicpc.net/problem/14942/
 *
 */
public class Boj14942 {
    private static ArrayList<Node>[] tree;
    private static int[][] parent;
    private static long[] dist;
    private static int[] deep;
    private static int[] ant;
    private static boolean[] visit;

    private static int N;
    private static final String NEW_LINE = "\n";

    private static class Node{
        int node;
        long cost;

        public Node(int node, long cost){
            this.node = node;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        N = in.readInt();

        tree = new ArrayList[N];
        parent = new int[N][21];
        dist = new long[N];
        deep = new int[N];
        visit = new boolean[N];

        ant = new int[N];

        for(int i = 0; i < N; i++){
            tree[i] = new ArrayList<>();
            ant[i] = in.readInt();
        }

        int loop = N - 1;
        while(loop-- > 0) {
            int node1 = in.readInt() - 1;
            int node2 = in.readInt() - 1;
            long cost = in.readLong();

            tree[node1].add(new Node(node2, cost));
            tree[node2].add(new Node(node1, cost));
        }

        dfs(0, 0);
        connecting();

        StringBuilder sb = new StringBuilder();

        sb.append(1).append(NEW_LINE);
        for(int node = 1; node < N; node++){
            sb.append(find(node) + 1).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int current, int depth){
        deep[current] = depth;
        visit[current] = true;

        for(Node next: tree[current]){
            if(visit[next.node]) continue;

            parent[next.node][0] = current;
            dist[next.node] = dist[current] + next.cost;            // prefix sum
            dfs(next.node, depth + 1);
        }
    }

    private static void connecting(){
        for(int p = 1; p < 21; p++){
            for(int cur = 0; cur < N; cur++){
                parent[cur][p] = parent[parent[cur][p - 1]][p - 1];
            }
        }
    }

    private static int find(int x){
        long c = ant[x];

        for(int i = 20; i >= 0; i--){
            int jump = 1 << i;

            long diff = dist[x] - dist[parent[x][i]];
            if(jump > deep[x] || c < diff) continue;        // can reach & energy is enough?

            x = parent[x][i];
            c -= diff;
        }

        return x;
    }

    private static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public long readLong() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }
}

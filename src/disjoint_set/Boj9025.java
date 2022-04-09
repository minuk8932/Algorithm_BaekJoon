package disjoint_set;

import common.Node;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author exponential-e
 * 백준 9025번: Widest path
 *
 * @see https://www.acmicpc.net/problem/9025
 *
 */
public class Boj9025 {

    private static final String NEW_LINE = "\n";
    private static final int INF = 1_000_000_000;

    private static List<Node<Integer, Integer>>[] path;

    private static int[][] input;
    private static int[] dist;
    private static int[] parent;

    private static PriorityQueue<Node<Integer, Integer>> pq;

    public static void main(String[] args) throws Exception {
        InputReader in = new InputReader(System.in);
        int T = in.readInt();

        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            int n = in.readInt();
            int m = in.readInt();
            int s = in.readInt() - 1;
            int t = in.readInt() - 1;

            init(n);

            while(m-- > 0) {
                int from = in.readInt() - 1;
                int to = in.readInt() - 1;
                int cost = in.readInt();

                input[from][to] = Math.min(input[from][to], cost);
                input[to][from] = input[from][to];
            }

            offline();
            mst();

            bfs(s);
            sb.append(search(t)).append(NEW_LINE);
        }

        System.out.println(sb);
    }

    private static int search(int snk) {
        int answer = INF;

        Queue<Node<Integer, Integer >> q = new ArrayDeque<>();
        q.offer(new Node.Builder(snk).cost(dist[snk]).build());

        while(!q.isEmpty()) {
            Node<Integer, Integer> current = q.poll();

            for(Node<Integer, Integer> prev: path[current.getNode()]) {
                if(dist[prev.getNode()] != dist[current.getNode()] - prev.getCost()) continue;
                answer = Math.min(prev.getCost(), answer);

                q.offer(new Node.Builder(prev.getNode()).cost(dist[prev.getNode()]).build());
            }
        }

        return answer;
    }

    private static void offline() {
        for(int from = 0; from < input.length; from++) {
            for(int to = from + 1; to < input.length; to++) {
                if(input[from][to] == INF) continue;
                pq.offer(new Node.Builder(from).another(to).cost(-input[from][to]).build());
            }
        }
    }

    private static void bfs(int src) {
        Queue<Node<Integer, Integer>> q = new ArrayDeque<>();
        q.offer(new Node.Builder(src).cost(0).build());
        dist[src] = 0;

        while(!q.isEmpty()) {
            Node<Integer, Integer> current = q.poll();

            for(Node<Integer, Integer> next: path[current.getNode()]) {
                if(dist[next.getNode()] <= dist[current.getNode()] + next.getCost()) continue;
                dist[next.getNode()] = dist[current.getNode()] + next.getCost();

                q.offer(new Node.Builder(next.getNode()).cost(dist[next.getNode()]).build());
            }
        }
    }

    /**
     *
     * Maximum cost Spanning Tree
     *
     * Linkage each vertex, cost descending order
     *
     */
    private static void mst() {
        while(!pq.isEmpty()) {
            Node<Integer, Integer> current = pq.poll();

            if(merged(current.getNode(), current.getAnother())) continue;
            path[current.getNode()].add(
                new Node.Builder(current.getAnother())
                    .cost(-current.getCost())
                    .build());

            path[current.getAnother()].add(
                new Node.Builder(current.getNode())
                    .cost(-current.getCost())
                    .build());
        }
    }

    private static int find(int x) {
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merged(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return true;

        if(parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }

        return false;
    }

    private static void init(int n) {
        path = new ArrayList[n];
        parent = new int[n];
        dist = new int[n];
        input = new int[n][n];

        pq = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));

        for(int i = 0; i < n; i++){
            path[i] = new ArrayList<>();
            parent[i] = -1;
            dist[i] = INF;
            Arrays.fill(input[i], INF);
        }
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

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public interface SpaceCharFilter {
            boolean isSpaceChar(int ch);
        }
    }
}
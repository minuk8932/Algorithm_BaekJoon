package dijkstra;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

/**
 *
 * @author exponential-e
 * 백준 16118번: 달빛 여우
 *
 * @see https://www.acmicpc.net/problem/16118
 *
 */
public class Boj16118 {

    private static ArrayList<Node>[] path;
    private static long[][] wolf;
    private static long[] fox;

    private static final long INF = 10_000_000_000L;

    private static class Node implements Comparable<Node>{
        int node;
        long cost;
        long velocity;

        public Node(int node, long cost, long velocity) {
            this.node = node;
            this.cost = cost;
            this.velocity = velocity;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost < n.cost ? -1: 1;
        }
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        int N = in.readInt();
        int M = in.readInt();

        path = new ArrayList[N];
        wolf = new long[N][2];
        fox = new long[N];

        for(int i = 0; i < N; i++) {
            path[i] = new ArrayList<>();
            wolf[i][0] = INF;
            wolf[i][1] = INF;
            fox[i] = INF;
        }

        while(M-- > 0) {
            int a = in.readInt() - 1;
            int b = in.readInt() - 1;
            long d = in.readLong();

            path[a].add(new Node(b, d, 1L));
            path[b].add(new Node(a, d, 1L));
        }

        foxPath(2);
        wolfPath(1, 4);

        System.out.println(foxFast());
    }

    private static int foxFast() {
        int count = 0;

        for(int i = 0; i < wolf.length; i++) {
            if(fox[i] < Math.min(wolf[i][1], wolf[i][0])) count++;
        }

        return count;
    }

    private static void foxPath(long val) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 1));

        fox[0] = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            if(fox[current.node] < current.cost) continue;

            for(Node next: path[current.node]) {
                if(fox[next.node] <= fox[current.node] + next.cost * val) continue;
                fox[next.node] = fox[current.node] + next.cost * val;

                pq.offer(new Node(next.node, fox[next.node], 1));
            }
        }
    }

    /**
     *
     * make shortest path by 1, 4
     *
     * line 124 ~ 125: two cases available
     *
     * @param v1
     * @param v2
     */
    private static void wolfPath(long v1, long v2) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, v1));

        wolf[0][0] = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            int idx = current.velocity == v2 ? 1: 0;
            if(wolf[current.node][idx] < current.cost) continue;

            for(Node next: path[current.node]) {
                int nextIdx = current.velocity == v2 ? 0: 1;

                if(wolf[next.node][nextIdx] <= wolf[current.node][idx] + next.cost * current.velocity) continue;
                wolf[next.node][nextIdx] = wolf[current.node][idx] + next.cost * current.velocity;

                pq.offer(new Node(next.node, wolf[next.node][nextIdx], current.velocity == v1 ? v2: v1));
            }
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

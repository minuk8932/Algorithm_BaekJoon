package dynamic_programming;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 *
 * @author exponential-e
 * 백준 14550번: 마리오 파티
 *
 * @see https://www.acmicpc.net/problem/14550
 *
 */
public class Boj14550 {

    private static final int INF = -1_000_000_000;
    private static final String NEW_LINE = "\n";

    private static int[][] dp;
    private static int[] board;

    private static int S;

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        StringBuilder sb = new StringBuilder();

        while(true) {
            int N = in.readInt();
            if(N == 0) break;

            S = in.readInt();
            int T = in.readInt();
            init(N, T);

            board = new int[N + 2];
            for(int i = 1; i <= N; i++) {
                board[i] = in.readInt();
            }

            sb.append(recursion(T, N + 1)).append(NEW_LINE);
        }

        System.out.println(sb);
    }

    private static int recursion(int turn, int current) {
        if(current <= 0 && turn >= 0) return 0;
        if(turn < 0) return INF << 1;

        if(dp[current][turn] != INF) return dp[current][turn];
        int answer = INF;

        for(int jump = S; jump > 0; jump--) {
            answer = Math.max(answer
                , recursion(turn - 1, current - jump) + board[current]);
        }

        return dp[current][turn] = answer;
    }

    private static void init(int n, int t) {
        dp = new int[n + 2][t + 1];

        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], INF);
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
            public boolean isSpaceChar(int ch);
        }
    }
}
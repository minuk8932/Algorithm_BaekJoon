package math;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

/**
 *
 * @author exponential-e
 * 백준 20501번: FaceBook
 *
 * @see https://www.acmicpc.net/problem/20501
 *
 */
public class Boj20501 {
    private static final String NEW_LINE = "\n";
    private static long[][] bits;

    /**
     * line 26: input divide by 60 chars
     *
     * @param args
     */
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        int N = in.readInt();

        int size = N / 60;
        bits = new long[N][size + 1];

        for(int i = 0; i < N; i++) {
            String input = in.readString();
            int start = input.length() - 1;
            int end = Math.max(0, start - 59);

            charToLong(i, start, end, input);
        }

        StringBuilder sb = new StringBuilder();
        int Q = in.readInt();
        while(Q-- > 0) {
            int a = in.readInt() - 1;
            int b = in.readInt() - 1;

            sb.append(bitCompare(a, b)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    /**
     * line 54: find common 1bits
     * line 58: v's 1 value count is common friends (in binary data)
     *
     * @param a
     * @param b
     * @return
     */
    private static int bitCompare(int a, int b) {
        int count = 0;

        for(int i = 0; i < bits[a].length; i++) {
            long v = bits[a][i] & bits[b][i];

            while(v > 0) {
                count += v % 2;
                v >>= 1;
            }
        }

        return count;
    }

    /**
     * convert char(60) to long value by binary
     *
     * @param idx
     * @param s
     * @param e
     * @param input
     */
    private static void charToLong(int idx, int s, int e, String input) {
        for(int j = bits[idx].length - 1; j >= 0; j--) {
            long value = 0;
            long pow = 1;

            for(int k = s; k >= e; k--) {
                value += (input.charAt(k) - '0') * pow;
                pow <<= 1L;
            }

            bits[idx][j] = value;

            s = e - 1;
            e = Math.max(0, s - 59);
        }
    }

    /**
     * fast IO
     */
    private static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private InputReader.SpaceCharFilter filter;

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

        public String readString() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String next() {
            return readString();
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }
}

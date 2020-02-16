package string_handle;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 *
 * @author exponential-e
 * 백준 5648번: 역원소 정렬
 *
 * @see https://www.acmicpc.net/problem/5648/
 *
 */
public class Boj5648 {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        InputReader in = new InputReader(System.in);
        int n = in.readInt();

        long[] arr = new long[n];
        for(int i = 0; i < n; i++){
            arr[i] = reverse(in.readString().toCharArray());
        }

        Arrays.sort(arr);
        print(arr);
    }

    private static void print(long[] arr){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < arr.length; i++){
            sb.append(arr[i]).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static long reverse(char[] c){
        StringBuilder sb = new StringBuilder();

        for(int i = c.length - 1; i >= 0; i--){
            sb.append(c[i]);
        }

        return Long.parseLong(sb.toString());
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

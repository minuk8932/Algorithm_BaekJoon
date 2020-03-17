package segment_tree;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

/**
 *
 * @author exponential-e
 * 백준 9345번: DVDs
 *
 * @see https://www.acmicpc.net/problem/9345/
 *
 */
public class Boj9345 {
    private static int[] min;
    private static int[] max;
    private static int[] tree;

    private static int N, S = 1;

    private static final String NEW_LINE = "\n";
    private static final int INF = 1_000_000_000;

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        int T = in.readInt();

        StringBuilder sb = new StringBuilder();

        while(T-- > 0){
            N = in.readInt();
            int K = in.readInt();

            init();

            for(int i = S; i < S + N; i++){
                min[i] = i - S;
                max[i] = i - S;
                tree[i - S] = i - S;
            }

            update();

            while(K-- > 0){
                int cmd = in.readInt();
                int A = in.readInt();
                int B = in.readInt();

                if(cmd == 0){
                    swap(A, B);                             // swap
                    min[A + S] = tree[A];
                    min[B + S] = tree[B];
                    max[A + S] = tree[A];
                    max[B + S] = tree[B];
                    minmaxUpdate(A + S, B + S); // update
                }
                else{
                    int[] result = {getMin(A + S, B + S), getMax(A + S, B + S)};        // get min max
                    sb.append(result[0] == A && B == result[1] ? "YES": "NO").append(NEW_LINE);
                }
            }
        }

        System.out.println(sb.toString());
    }

    private static void init(){
        while(S <= N) S <<= 1;

        min = new int[S * 2];
        max = new int[S * 2];
        tree = new int[N];

        for(int i = 0; i < min.length; i++){
            min[i] = INF;
        }
    }

    private static int[] makeSon(int node){
        int son = node * 2;
        return new int[]{son, ++son};
    }

    private static void update(){
        for(int i = S - 1; i > 0; i--){
            int[] son = makeSon(i);
            min[i] = Math.min(min[son[0]], min[son[1]]);
            max[i] = Math.max(max[son[0]], max[son[1]]);
        }
    }

    private static void swap(int x, int y){
        int swap = tree[x];
        tree[x] = tree[y];
        tree[y] = swap;
    }

    private static void minmaxUpdate(int left, int right){
        for(int i = left; i > 0; i /= 2) {              // update with sons
            int idx = i / 2;
            min[idx] = Math.min(min[i], min[i ^ 1]);
            max[idx] = Math.max(max[i], max[i ^ 1]);
        }

        for(int i = right; i > 0; i /= 2) {
            int idx = i / 2;
            min[idx] = Math.min(min[i], min[i ^ 1]);
            max[idx] = Math.max(max[i], max[i ^ 1]);
        }
    }

    private static int getMax(int left, int right) {
        int res = 0;

        while(left < right) {
            if(left % 2 == 1) res = Math.max(res, max[left++]);
            if(right % 2 == 0) res = Math.max(res, max[right--]);

            left /= 2;
            right /= 2;
        }

        if(left == right) res = Math.max(max[left], res);
        return res;
    }

    private static int getMin(int left, int right) {
        int res = INF;

        while(left < right) {
            if(left % 2 == 1) res = Math.min(res, min[left++]);
            if(right % 2 == 0) res = Math.min(res, min[right--]);

            left /= 2;
            right /= 2;
        }

        if(left == right) res = Math.min(min[left], res);
        return res;
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

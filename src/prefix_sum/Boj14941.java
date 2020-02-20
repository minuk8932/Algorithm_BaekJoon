package prefix_sum;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 *
 * @author exponential-e
 * 백준 14941번: 호기심
 *
 * @see https://www.acmicpc.net/problem/14941/
 *
 */
public class Boj14941 {
    private static ArrayList<Long>[] prefix = new ArrayList[2];

    private static final String NEW_LINE = "\n";
    private static final int LEN = 100_001;

    private static int[] index = new int[LEN];
    private static boolean[] prime = new boolean[LEN];

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        StringBuilder sb = new StringBuilder();
        int n = in.readInt();

        init();

        while (n-- > 0){
            int a = in.readInt();
            int b = in.readInt();

            while(!prime[a]) a++;           // find adjacent prime
            while(!prime[b]) b--;

            int idx1 = index[a];
            int idx2 = index[b];

            int ii = idx1 % 2;
            sb.append(prefix[ii].get(idx2) - prefix[ii].get(idx1 - 1)).append(NEW_LINE);        // get segment sum
        }

        System.out.println(sb.toString());
    }

    private static void init(){
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;

        int loop = (int) (Math.sqrt(LEN) + 1);

        for(int i = 2; i < loop; i++){
            if(!prime[i]) continue;

            for(int j = i + i; j < LEN; j += i){
                prime[j] = false;
            }
        }

        for(int i = 0; i < 2; i++) {
            prefix[i] = new ArrayList<>();
            prefix[i] = getSum(i);
        }
    }

    private static ArrayList<Long> getSum(int idx){
        ArrayList<Long> list = new ArrayList<>();
        list.add(0L);
        int count = 1;

        for(int i = 0; i < LEN; i++){               // make prefix sum
            if(!prime[i]) continue;
            long val = i;

            if(count % 2 == idx) val *= 3;
            else val *= -1;

            list.add(val + list.get(count - 1));
            index[i] = count;
            count++;
        }

        return list;
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
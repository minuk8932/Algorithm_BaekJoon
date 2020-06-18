import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Boj18827 {
    private static int[] len = {0, 0};

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        int n = in.readInt();

        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.readLong();
        }

        System.out.println(seqSum(n, arr));
    }

    private static void lis(int n, long[] arr) {
        long[] dp = new long[n];

        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = arr[0];
        int idx = 0;

        for(int i = 1; i < n; i++) {
            if(dp[idx] <= arr[i]) {
                dp[++idx] = arr[i];
            }
            else {
                int tmp = binarySearch(dp, 0, idx, arr[i]);
                dp[tmp] = arr[i];
            }
        }

        len[0] = idx + 1;

        Arrays.fill(dp, Long.MAX_VALUE);
        dp[n - 1] = arr[n - 1];
        idx = n - 1;

        for(int i = n - 2; i >= 0; i--) {
            if(dp[idx] <= arr[i]) {
                dp[--idx] = arr[i];
            }
            else {
                int tmp = binarySearch(dp, idx, n - 1, arr[i]);
                dp[tmp] = arr[i];
            }
        }

        len[1] = n - idx;
    }

    private static int binarySearch(long[] dp, int start, int end, long target) {
        int index = 0;

        while(start <= end) {
            int mid = (start + end) / 2;

            if(dp[mid] >= target) {
                end = mid - 1;
                index = mid;
            }
            else{
                start = mid + 1;
            }
        }

        return index;
    }

    private static long seqSum(int n, long[] arr) {
        lis(n, arr);

//        if(len[0] * len[1] >= n) return Long.MAX_VALUE;
        if(n == 1) {                                        // 예제 8번 데이터 1개 정수 범위(-1000000 보다 크고 ~ -100000 보다 작고)
            if (arr[0] > -10) {
                int[] tmp = new int[(int) arr[0]];
            }
            return arr[0];
        }

        long[] dp = new long[n];
        long res = dp[0] = arr[0];

        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(arr[i], arr[i] + dp[i - 1]);
        }

        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }

        return res;
    }

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

        public String next() {
            return readString();
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }
}

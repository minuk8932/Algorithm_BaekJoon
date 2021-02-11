import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Boj2464 {

    private static ArrayDeque<Long> stack = new ArrayDeque<>();

    private static final long INF = Long.MAX_VALUE;
    private static long smaller, bigger;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long A = Long.parseLong(br.readLine());

        longToBinary(A);
        switching(A);
        System.out.println((smaller == -1 ? 0: smaller) + " " + (bigger == INF ? 0: bigger));
    }

    private static void switching(long a) {
        int size = stack.size();
        long[] bin = new long[size];

        int idx = 0;
        while(!stack.isEmpty()) {
            bin[idx] = stack.pop();
            idx++;
        }

        smaller = -1;
        bigger = Long.MAX_VALUE;
    }

    private static void longToBinary(long a) {
        long loop = a;

        while(loop > 0) {
            stack.push(loop % 2);
            loop /= 2;
        }
    }
}

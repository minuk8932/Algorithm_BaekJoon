package two_pointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 22114번: 창영이와 점프
 *
 * @see https://www.acmicpc.net/problem/22114
 *
 */
public class Boj22114 {

    private static int[] L;
    private static final BiPredicate<Integer, Integer> COMPARE = (x, y) -> x < y - 1;
    private static final BiPredicate<Boolean, Boolean> AND = (x, y) -> x && y;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int K = Integer.parseInt(st.nextToken());

        L = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N - 1; i++) {
            L[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(twoPointer(N, K));
    }

    /**
     *
     * Two pointer
     *
     * line 60 ~ 63: pre define functions (adder, compare, switcher, getMax)
     * line 67 ~ 72: just move forward
     * line 74 ~ 90: when CY can't move by K jump case
     *
     * @param N
     * @param K
     * @return
     */
    private static int twoPointer(final int N, final int K) {
        int start = 0, end = 0;
        int blocks = 1;

        boolean jumpped = false;
        int result = 0;
        int flag = 0;

        final Function<Integer, Integer> ADDER = x -> x + 1;
        final Function<Boolean, Boolean> SWITCHER = x -> !x;

        final BiFunction<Integer, Integer, Integer> MAX = (x, y) -> Math.max(x, y);

        while (outOfRange(start, end, N)) {

            if(COMPARE.test(L[end], K + 2)){
                blocks = ADDER.apply(blocks);
                end = ADDER.apply(end);

                continue;
            }

            if(jumpped) {
                start = ADDER.apply(flag);
                result = MAX.apply(result, blocks);
                jumpped = SWITCHER.apply(jumpped);

                end = start;

                blocks = 1;
                flag = 0;
            }
            else {
                blocks = ADDER.apply(blocks);
                jumpped = SWITCHER.apply(jumpped);

                flag = end;
                end = ADDER.apply(end);
            }
        }

        return MAX.apply(result, blocks);
    }

    private static boolean outOfRange(int a, int b, final int N) {
        return AND.test(COMPARE.test(a, N), COMPARE.test(b, N));
    }
}

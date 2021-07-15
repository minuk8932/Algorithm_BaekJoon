package two_pointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.BiFunction;
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
    private static BiFunction<Integer, Integer, Boolean> compare;

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
     * line 56 ~ 60: pre define functions (adder, compare, switcher, getMax)
     * line 64 ~ 69: just move forward
     * line 71 ~ 87: when CY can't move by K jump case
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

        Function<Integer, Integer> adder = x -> x + 1;
        Function<Boolean, Boolean> switcher = x -> !x;

        BiFunction<Integer, Integer, Integer> getMax = (x, y) -> Math.max(x, y);
        compare = (x, y) -> x < y - 1;

        while (outOfRange(start, end, N)) {

            if(compare.apply(L[end], K + 2)){
                blocks = adder.apply(blocks);
                end = adder.apply(end);

                continue;
            }

            if(jumpped) {
                start = adder.apply(flag);
                result = getMax.apply(result, blocks);
                jumpped = switcher.apply(jumpped);

                end = start;

                blocks = 1;
                flag = 0;
            }
            else {
                blocks = adder.apply(blocks);
                jumpped = switcher.apply(jumpped);

                flag = end;
                end = adder.apply(end);
            }
        }

        return getMax.apply(result, blocks);
    }

    private static boolean outOfRange(int a, int b, final int N) {
        return compare.apply(a, N) && compare.apply(b, N);
    }
}

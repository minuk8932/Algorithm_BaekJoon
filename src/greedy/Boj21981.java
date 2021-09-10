package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 22981번: 휴먼 파이프라인
 *
 * @see https://www.acmicpc.net/problem/22981
 *
 */
public class Boj21981 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = PARSE_INT.apply(st.nextToken());
        long K = PARSE_Long.apply(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long[] work = ARRY.apply(N);
        for(int i = 0; i < N; i++) {
            work[i] = PARSE_Long.apply(st.nextToken());
        }

        Arrays.sort(work);
        System.out.println(working(K, work));
    }

    /**
     *
     * Working
     *
     * line 54: compare left & right sorted array
     *
     * @param k
     * @param w
     * @return
     */
    private static long working(long k, long[] w) {
        long fast = Long.MAX_VALUE;

        long joule = w[0];
        long size = 1;
        long len = w.length;

        for(int i = 1; i < w.length; i++) {
            long left = joule * size;
            long right = w[i] * (len - size);

            long total = left + right;
            fast = Math.min(fast, k / total + (k % total != 0 ? 1: 0));

            size++;
        }

        return fast;
    }

    private static final Function<String, Integer> PARSE_INT = Integer::parseInt;
    private static final Function<String, Long> PARSE_Long = Long::parseLong;
    private static final Function<Integer, long[]> ARRY = long[]::new;
}

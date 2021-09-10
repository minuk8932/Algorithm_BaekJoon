package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 22973번: 점프 숨바꼭질
 *
 * @see https://www.acmicpc.net/problem/22973
 *
 */
public class Boj22973 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long K = PARSE.apply(br.readLine());

        System.out.println(pow(Math.abs(K)));
    }

    private static long pow(long k) {
        if(k == 0) return 0;
        if(k % 2 == 0) return -1;

        long count = 0L;
        long two = 0L;

        while(two < k) {
            two += (1L << count);
            count++;
        }

        return count;
    }

    private static final Function<String, Long> PARSE = Long::parseLong;

}

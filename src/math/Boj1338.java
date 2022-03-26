package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1338번: 알 수 없는 번호
 *
 * @see https://www.acmicpc.net/problem/1338
 *
 */
public class Boj1338 {

    private static final String NO = "Unknwon Number";
    private static final long INF = 1_000_000_000_000_000_000L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long from = Long.parseLong(st.nextToken());
        long to = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long x = Math.abs(Long.parseLong(st.nextToken()));
        long y = Long.parseLong(st.nextToken());

        System.out.println(guess(Math.min(from, to), Math.max(from, to), x, y));
    }

    private static String guess(long from, long to, long x, long y) {
        if(y < 0 || y >= x) return NO;

        boolean check = (x << 1L) <= (to - from);
        if(check) return NO;

        long mod = (from % x + x) % x;
        if (mod != y) {
            from += y - mod;
            if (mod > y) from += x;
        }

        long value = INF;
        int count = 0;

        for (long i = from; i <= to; i += x) {
            if ((i % x + x) % x == y){
                value = i;
                count++;
            }

            if (count > 1) return NO;
        }

        return value == INF ? NO: (value + "");
    }
}

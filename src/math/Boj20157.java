package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20157번: 화살을 쏘자
 *
 * @see https://www.acmicpc.net/problem/20157
 *
 */
public class Boj20157 {
    private static final long CIPHER = 10_000_000L;
    private static HashMap<Long, Integer> positive = new HashMap<>();
    private static HashMap<Long, Integer> negative = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            settings(x, y, Math.abs(GCD(x, y)));
        }

        System.out.println(Math.max(mostTilt(positive), mostTilt(negative)));
    }

    private static void settings(long x, long y, long gcd) {
        x /= gcd;
        y /= gcd;                       // make tilt

        if(x < 0) makeKey(y > 0 ? x: -x, -y, negative);     // tied positive(1, 4) & negative(2, 3)
        else makeKey(x > 0 ? -x: x, y, positive);
    }

    private static void makeKey(long x, long y, HashMap<Long, Integer> map) {
        map.merge(x * CIPHER + y, 1, Integer::sum);
    }

    private static int mostTilt(HashMap<Long, Integer> map) {
        int result = 0;

        for(long key: map.keySet()) {
            if(map.get(key) > result) result = map.get(key);
        }

        return result;
    }

    private static long GCD(long a, long b) {
        if(b == 0) return a;
        return GCD(b, a % b);
    }
}

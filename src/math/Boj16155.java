package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 16155번: 업힐과 가희
 *
 * @see https://www.acmicpc.net/problem/16155
 *
 */
public class Boj16155 {

    private static final String DIV = "/";
    private static final long INF = 1_000_000_000L;

    private static int N;

    private static class Coordinate {
        long x;
        long y;

        public Coordinate(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()) + 1;

        Coordinate[] checkPoint = new Coordinate[N + 1];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            checkPoint[i] = new Coordinate(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        }

        checkPoint[N] = new Coordinate(INF, 0);

        StringTokenizer st = new StringTokenizer(br.readLine());
        long from = Long.parseLong(st.nextToken());
        long to = Long.parseLong(st.nextToken());

        System.out.println(tiltAverage(checkPoint, from, to));
    }

    private static long gcd(long a, long b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }

    /**
     *
     * Tilt Average
     *
     * line 71 ~ 72: make tilt
     * line 78: make Avg
     *
     * @param cp
     * @param front
     * @param back
     * @return
     */
    private static String tiltAverage(Coordinate[] cp, long front, long back) {
        Coordinate c1 = makeCoors(front, cp);
        Coordinate c2 = makeCoors(back, cp);

        long x = (back - front) * c1.x * c2.x;
        long y = Math.abs(c1.y * c2.x - c2.y * c1.x);

        long gcd = gcd(x, y);
        Coordinate result = new Coordinate(x / gcd, y / gcd);

        return result.y == 0 || result.x == 1 ? result.y + "" : result.y + DIV + result.x;
    }

    private static Coordinate makeCoors(long origin, Coordinate[] cp) {
        int tilt = 0;

        while(origin >= cp[tilt].x) {
            tilt++;
        }

        tilt -= tilt == N ? 2: 1;

        Coordinate c = new Coordinate(0, 0);
        c.x = cp[tilt + 1].x - cp[tilt].x;
        c.y = (origin - cp[tilt].x) * (cp[tilt + 1].y - cp[tilt].y) + cp[tilt].y * c.x;

        return c;
    }
}

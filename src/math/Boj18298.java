package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18298번: Icebergs
 *
 * @see https://www.acmicpc.net/problem/18298/
 *
 */
public class Boj18298 {
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
        int N = Integer.parseInt(br.readLine());

        double sqr = 0;

        while(N-- > 0) {
            int P = Integer.parseInt(br.readLine());

            Coordinate[] coor = new Coordinate[P];
            for(int i = 0; i < P; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                coor[i] = new Coordinate(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
            }

            double size = 0;
            Coordinate s = coor[0];
            for(int i = 1; i < P; i++) {
                size += (s.x - coor[i].x) * (s.y + coor[i].y) / 2.0;        // make polygon sizes
                s = coor[i];
            }

            size += (s.x - coor[0].x) * (s.y + coor[0].y) / 2.0;
            sqr += Math.abs(size);
        }

        System.out.println((long) sqr);
    }
}

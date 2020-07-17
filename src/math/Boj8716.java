package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 8716번: Pole
 *
 * @see https://www.acmicpc.net/problem/8716/
 *
 */
public class Boj8716 {

    private static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Rectangle {
        Coordinate c1;
        Coordinate c2;

        public Rectangle(Coordinate c1, Coordinate c2) {
            this.c1 = c1;
            this.c2 = c2;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        Rectangle r1 = new Rectangle(new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())),
                new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

        st = new StringTokenizer(br.readLine());
        Rectangle r2 = new Rectangle(new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())),
                new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

        System.out.println(overlap(new Coordinate(r1.c1.x, r1.c2.x), new Coordinate(r2.c1.x, r2.c2.x))
                * overlap(new Coordinate(r1.c1.y, r1.c2.y), new Coordinate(r2.c1.y, r2.c2.y)));
    }

    private static long overlap(Coordinate one, Coordinate another) {
        long size = 0;
        boolean[] cover = new boolean[1_000_001];

        int start = Math.min(one.x, one.y);
        int end = Math.max(one.x, one.y);
        for(int x = start; x < end; x++) {
            cover[x] = true;
        }

        start = Math.min(another.x, another.y);
        end = Math.max(another.x, another.y);
        for(int x = start; x < end; x++) {
            if(cover[x]) size++;
        }

        return size;
    }
}

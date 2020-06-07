import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj16155 {
    private static final String DIV = "/";

    private static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Tilt {
        int s;
        int e;
        Coordinate difference;

        public Tilt(int s, int e, Coordinate difference) {
            this.s = s;
            this.e = e;
            this.difference = difference;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Coordinate[] checkPoint = new Coordinate[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        Coordinate start = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            checkPoint[i] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        System.out.println(tiltAverage(start, checkPoint, from, to));
    }

    private static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }

    private static StringBuilder tiltAverage(Coordinate s, Coordinate[] cp, int f, int t) {
        ArrayList<Tilt> data = new ArrayList<>();
        for(int i = 0; i < cp.length; i++) {
            data.add(new Tilt(s.x, cp[i].x, makeDiffer(s, cp[i])));
            s = cp[i];
        }

        int dx = 0, dy = 0;
        for(Tilt current: data) {
            if((current.s >= f && current.e <= t) ||
                    (current.s <= f && current.e >= f) ||
                    (current.s <= t && current.e >= t)) {
                dx += current.difference.x;
                dy += current.difference.y;
            }
        }

        dx = Math.abs(dx);
        dy = Math.abs(dy);

        int gcd = gcd(dx, dy);
        dx /= gcd;
        dy /= gcd;

        StringBuilder sb = new StringBuilder();
        if(dy == 0) return sb.append(0);
        if(dx == 1) return sb.append(dy);
        return sb.append(dy).append(DIV).append(dx);
    }

    private static Coordinate makeDiffer(Coordinate c1, Coordinate c2) {
        return new Coordinate(c2.x - c1.x, c2.y - c1.y);
    }
}

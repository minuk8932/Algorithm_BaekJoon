import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10985 {
    private static final String NEW_LINE = "\n";
    private static final double CIPHER = 1_000.0;

    private static final Coordinate ZERO = new Coordinate(0, 0);

    private static class Coordinate {
        double x;
        double y;

        public Coordinate(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            Coordinate rex = new Coordinate(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
            Coordinate chk = new Coordinate(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
            double R = Double.parseDouble(st.nextToken());

            sb.append(hunt(rex, chk, R)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static double hunt(Coordinate src, Coordinate snk, double r) {
        double a = src.y - snk.y;
        double b = src.x - src.x;
        double c = b * src.y - a * src.x;

        double d = distancePointToLine(ZERO, a, -b, c);

        if(d >= 0) {
            return Math.round(Math.sqrt(distancePointToPoint(src, snk)) * CIPHER) / CIPHER;
        }
        else {
            double dl = r - distancePointToLine(snk, a, -b, c);
            double[] dr = {distancePointToPoint(ZERO, snk), distancePointToPoint(ZERO, src)};
            double[] dist = {Math.sqrt(distancePointToPoint(ZERO, snk) - Math.pow(r, 2)),
                    Math.sqrt(distancePointToPoint(ZERO, src) - Math.pow(r, 2))};

            double pi = Math.acos(dl / dr[0]);

            return dist[0] + dist[1] + 2 * Math.PI * r * 1 / 360;
        }
    }

    private static double distancePointToLine(Coordinate point, double a, double b, double c) {
        return Math.abs(point.x * a + point.y * b + c) / Math.sqrt(a * a + b * b);
    }

    private static double distancePointToPoint(Coordinate c1, Coordinate c2) {
        return Math.pow(c1.x - c2.x, 2) + Math.pow(c1.y - c2.y, 2);
    }
}

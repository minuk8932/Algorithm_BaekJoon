package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 9319번: 도청 장치
 *
 * @see https://www.acmicpc.net/problem/9319/
 *
 */
public class Boj9319 {
    private static final String NULL = "NOISE";
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    private static class Coordinate {
        double x;
        double y;
        double s;

        public Coordinate(double x, double y, double s) {
            this.x = x;
            this.y = y;
            this.s = s;
        }

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
            int N = Integer.parseInt(br.readLine());
            double B = Double.parseDouble(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            Coordinate listener = new Coordinate(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));

            Coordinate[] device = new Coordinate[N];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                device[i] = new Coordinate(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
            }

            sb.append(finder(N, B, listener, device)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static String finder(int n, double b, Coordinate src, Coordinate[] snk) {
        double total = 0;
        double[] power = new double[n];

        for(int i = 0; i < n; i++) {
            power[i] = snk[i].s / euclideanDistancePow(snk[i], src);            // make all sum
            total += power[i];
        }

        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if(power[i] <= 6 * (total - power[i] + b)) continue;                // find appropriate power
            result.add(i + 1);
        }

        if(result.size() == 0) return NULL;
        StringBuilder sb = new StringBuilder();

        for(int re: result) {
            sb.append(re).append(SPACE);
        }

        return sb.toString();
    }

    private static double euclideanDistancePow(Coordinate c1, Coordinate c2) {
        return (c1.x - c2.x) * (c1.x - c2.x) + (c1.y - c2.y) * (c1.y - c2.y);
    }
}

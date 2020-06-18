package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14583번: 이음줄
 *
 * @see https://www.acmicpc.net/problem/14583/
 *
 */
public class Boj14583 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Double H = Double.parseDouble(st.nextToken());
        Double V = Double.parseDouble(st.nextToken());

        getSize(H, V);
    }

    private static void getSize(double h, double v) {
        double r = euclidianDistance(h, (euclidianDistance(h, v) - h) * h / v);     // make folding size
        double c = h / r * (v - (euclidianDistance(h, v) - h) * h / v);

        r /= 2;

        r = Math.round(r * 100) / 100.0;
        c = Math.round(c * 100) / 100.0;

        System.out.println(r + " " + c);
    }

    private static double euclidianDistance(double a, double b) {
        return Math.sqrt(a * a + b * b);
    }
}

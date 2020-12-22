package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 7869번: 두 원
 *
 * @see https://www.acmicpc.net/problem/7869
 *
 */
public class Boj7869 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        double x1 = Double.parseDouble(st.nextToken());
        double y1 = Double.parseDouble(st.nextToken());
        double r1 = Double.parseDouble(st.nextToken());
        double x2 = Double.parseDouble(st.nextToken());
        double y2 = Double.parseDouble(st.nextToken());
        double r2 = Double.parseDouble(st.nextToken());

        System.out.println(square(x1, y1, r1, x2, y2, r2));
    }

    private static String square(double x1, double y1, double r1
            , double x2, double y2, double r2) {

        double d = distance(x1, y1, x2, y2);
        double result;

        if(bound(r1, r2, 1) <= d) {                 // not overlapped
            return "0.000";
        }
        else if(bound(r1, r2, -1) >= d) {           // overlapped whole
            result = Math.min(circle(r1), circle(r2));
        }
        else{
            double tied = (r1 * r1 - r2 * r2 + d) / (2 * Math.sqrt(d));
            result = sector(r1, tied) + sector(r2, Math.sqrt(d) - tied);
        }

        return String.format("%.3f", result);
    }

    private static double bound(double r1, double r2, double v){
        return (r1 + r2 * v) * (r1 + r2 * v);
    }

    private static double circle(double r) {
        return r * r * Math.PI;
    }

    private static double sector(double r, double d){
        return (Math.acos(d / r) * r * r - d * Math.sqrt(r * r - d * d));
    }

    private static double distance(double x, double y, double x1, double y1) {
        double diffx = x - x1;
        double diffy = y - y1;

        return diffx * diffx + diffy * diffy;
    }
}

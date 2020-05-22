package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2672번: 여러 직사각형 전체 면적 구하기
 *
 * @see https://www.acmicpc.net/problem/2672/
 *
 */
public class Boj2672 {
    private static ArrayList<Double> xPiece = new ArrayList<>();
    private static ArrayList<Double> yPiece = new ArrayList<>();

    private static class Rectangle{
        double x;
        double y;
        double w;
        double h;

        public Rectangle(double x, double y, double w, double h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Rectangle[] rec = new Rectangle[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            double w = Double.parseDouble(st.nextToken());
            double h = Double.parseDouble(st.nextToken());

            rec[i] = new Rectangle(x, y, w, h);
            xPiece.add(x);
            xPiece.add(x + w);
            yPiece.add(y);
            yPiece.add(y + h);
        }

        double size = bruteForce(N, rec);
        System.out.println(size == (int) size ? (int) size: String.format("%.2f", size));
    }

    private static double bruteForce(int n, Rectangle[] rec) {
        Collections.sort(xPiece);
        Collections.sort(yPiece);

        double square = 0;
        int[] size = {xPiece.size(), yPiece.size()};

        for (int i = 1; i < size[0]; i++) {
            for (int j = 1; j < size[1]; j++) {
                double[] xs = {xPiece.get(i - 1), xPiece.get(i)};
                double[] ys = {yPiece.get(j - 1), yPiece.get(j)};

                for (int k = 0; k < n; k++) {
                    if (outOfRange(xs[0], xs[1], ys[0], ys[1], rec[k])) continue;       // rec in range ?
                    square += (xs[1] - xs[0]) * (ys[1] - ys[0]);
                    break;
                }
            }
        }

        return square;
    }

    private static boolean outOfRange(double x1, double x2, double y1, double y2, Rectangle r) {
        return r.x > x1 || (r.x + r.w) < x2 || r.y > y1 || (r.y + r.h) < y2;
    }
}

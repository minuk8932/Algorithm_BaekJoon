package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 5545번: 최고의 피자
 *
 * @see https://www.acmicpc.net/problem/5545/
 *
 */
public class Boj5545 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        double A = Double.parseDouble(st.nextToken());
        double B = Double.parseDouble(st.nextToken());

        double C = Double.parseDouble(br.readLine());

        double[] D = new double[N];
        for(int i = 0; i < N; i++){
            D[i] = -Double.parseDouble(br.readLine());
        }

        System.out.println(calorie(A, B, C ,D));
    }

    private static int calorie(double a, double b, double c, double[] d){
        Arrays.sort(d);

        double result = c / a;
        double[] div = {c, a};

        for(int i = 0; i < d.length; i++){
            div[0] -= d[i];
            div[1] += b;

            double divv = div[0] / div[1];          // if next pizza calorie is less, then break
            if(result > divv) break;

            result = divv;
        }

        return (int) Math.floor(result);
    }
}

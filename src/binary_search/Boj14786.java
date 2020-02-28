package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14786번: Ax+Bsin(x)=C
 *
 * @see https://www.acmicpc.net/problem/14786/
 *
 */
public class Boj14786 {
    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        System.out.println(triangularFunction(A, B, C));
    }

    private static double triangularFunction(double a, double b, double c){
        double left = (c - b) / a;
        double right = (c + b) / a;
        double result = 0;

        for(; left <= right; left++){
            int start = 0, end = INF;

            while(start <= end){
                int mid = (start + end) / 2;

                double x = (mid / (double) INF) + left;         // make x with decimal point
                double temp = makeValue(a, b, x);

                if(temp > c) {
                    end = mid - 1;
                }
                else {
                    start = mid + 1;
                    result = x;
                }
            }
        }

        return result;
    }

    private static double makeValue(double a, double b, double x){
        return a * x + b * Math.sin(x);
    }
}

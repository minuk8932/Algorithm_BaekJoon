package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 8096번: Monochromatic Triangles
 *
 * @see https://www.acmicpc.net/problem/8096/
 *
 */
public class Boj8096 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] colored = new int[N];
        while(M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            colored[a]++;
            colored[b]++;
        }

        System.out.println(findMonochromaticTriangles(N, colored));
    }

    private static int findMonochromaticTriangles(int n, int[] c) {
        int total = n * (n - 1) * (n - 2) / 6;
        int except = 0;

        for(int i = 0; i < n; i++) {                // find 2:1 colored
            except += (c[i] * (n - c[i] - 1));
        }

        return total - except / 2;
    }
}

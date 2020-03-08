package line_sweeping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2304번: 창고 다각형
 *
 * @see https://www.acmicpc.net/problem/2304/
 *
 */
public class Boj2304 {
    private static int min = 10_000, max = 0, size = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] garage = new int[1_000];
        while(N-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            garage[x] = Integer.parseInt(st.nextToken());

            min = Math.min(min, x);
            max = Math.max(max, x);

            if (garage[x] > garage[size]) size = x;
        }

        System.out.println(make(N, garage));
    }

    private static int make(int n, int[] g){
        if(n == 1) return g[0];
        return  result(g, min, 1) + result(g, max, -1) + g[size];       // trace couple sides
    }

    private static int result(int[] g, int start, int adder){
        int sum = 0, h = 0;

        for (int i = start; i != size; i += adder) {
            h = Math.max(h, g[i]);
            sum += h;
        }

        return sum;
    }
}

package search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14912번: 숫자 빈도수
 *
 * @see https://www.acmicpc.net/problem/14912/
 *
 */
public class Boj14912 {
    private static int[] frequent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        System.out.println(search(n, d));
    }

    private static int search(int n, int d) {
        frequent = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            int loop = i;

            while(loop > 0) {               // get frequency
                frequent[loop % 10]++;
                loop /= 10;
            }
        }

        return frequent[d];
    }
}

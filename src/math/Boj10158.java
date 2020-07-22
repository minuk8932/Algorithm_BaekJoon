package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 10158번: 개미
 *
 * @see https://www.acmicpc.net/problem/10158/
 *
 */
public class Boj10158 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(br.readLine());

        System.out.println(rotate(w, p + t) + " " + rotate(h, q + t));
    }

    private static int rotate(int l, int sum) {
        int max = sum / l % 2;

        if(max == 0) return sum % l;
        else return l - sum % l;
    }
}

package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 19532번: 수학은 비대면 강의 입니다.
 *
 * @see https://www.acmicpc.net/problem/19532
 *
 */
public class Boj19532 {
    private static final String SPACE = " ";
    private static int c, f;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());

        System.out.println(inverse(a, b, d, e));
    }

    private static String inverse(int a, int b, int d, int e) {
        StringBuilder sb = new StringBuilder();
        return sb.append(make(e, b, 1 == 1) / make(a * e, b * d, 1 != 1))
                .append(SPACE).append(make(d, a, 1 == 1) / -make(a * e, b * d, 1 != 1)).toString();
    }

    private static int make(int a1, int a2, boolean flag) {
        if(flag) return c * a1 - a2 * f;
        else return a1 - a2;
    }
}

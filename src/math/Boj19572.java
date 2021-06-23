package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 19572번: 가뭄 (Small)
 *
 * @see https://www.acmicpc.net/problem/19572
 *
 */
public class Boj19572 {

    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long d1 = (long) (Double.parseDouble(st.nextToken()) * 100);
        long d2 = (long) (Double.parseDouble(st.nextToken()) * 100);
        long d3 = (long) (Double.parseDouble(st.nextToken()) * 100);

        System.out.println(raining(d1, d2, d3));
    }

    private static String raining(long d1, long d2, long d3) {
        long sum = (d1 + d2 + d3) >> 1;
        long a = sum - d3;
        long b = sum - d2;
        long c = sum - d1;

        if(notCorrect(a, b, c)) return "-1";

        StringBuilder sb = new StringBuilder();
        sb.append(1).append(NEW_LINE);
        sb.append((double) a / 100.0).append(SPACE)
                .append((double) b / 100.0).append(SPACE).append((double) c / 100.0);

        return sb.toString();
    }

    private static boolean notCorrect(long a, long b, long c) {
        return a <= 0 || b <= 0 || c <= 0;
    }
}

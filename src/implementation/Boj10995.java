package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 10995번: 별 찍기 20
 *
 * @see https://www.acmicpc.net/problem/10995
 *
 */
public class Boj10995 {

    private static final String STAR = "*";
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(starring(N));
    }

    private static String starring(int n) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++) {
            int mod = i % 2;
            int loop = (n << 1) - (mod == 0 ? 1: 0);

            final String[][] ARR = {{STAR, SPACE}, {SPACE, STAR}};
            int index = 0;

            while(loop-- > 0) {
                sb.append(ARR[mod][index % 2]);
                index++;
            }

            sb.append(NEW_LINE);
        }

        return sb.toString();
    }
}

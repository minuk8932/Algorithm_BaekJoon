package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 10990번: 별 찍기 15
 *
 * @see https://www.acmicpc.net/problem/10990
 *
 */
public class Boj10990 {
    private static final String SPACE = " ";
    private static final String STAR = "*";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(starring(N));
    }

    private static String starring(int n) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++) {
            int loop = n - i - 1;
            while(loop-- > 0) {
                sb.append(SPACE);
            }

            sb.append(STAR);

            loop = (i << 1) - 1;
            while(loop-- > 0) {
                sb.append(SPACE);
            }

            if(i != 0) sb.append(STAR);
            sb.append(NEW_LINE);
        }

        return sb.toString();
    }
}

package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 5101번: Sequences
 *
 * @see https://www.acmicpc.net/problem/5101/
 *
 */
public class Boj5101 {
    private static final String ERROR = "X";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            if(A == 0 && B == 0 && C ==0) break;

            int mod = (C - A) % B;
            if(mod != 0 || (A > C && B >= 0) || (A < C && B <= 0)) {                          // is Sequence?
                sb.append(ERROR).append(NEW_LINE);
                continue;
            }

            sb.append(Math.abs((C - A) / B) + 1).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }
}

package search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11170번: 0의 개수
 *
 * @see https://www.acmicpc.net/problem/11170/
 *
 */
public class Boj11170 {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            int count = from == 0 ? 1: 0;           // if start is 0

            for(int i = from; i <= to; i++) {
                int loop = i;

                while(loop > 0) {                   // counting
                    if(loop % 10 == 0) count++;
                    loop /= 10;
                }
            }

            sb.append(count).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }
}

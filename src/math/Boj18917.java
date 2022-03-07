package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18917번: 수열과 쿼리 38
 *
 * @see https://www.acmicpc.net/problem/18917
 *
 */
public class Boj18917 {

    private static final String NEW_LINE = "\n";
    private static Map<Long, Integer> A = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        long sum = 0L;
        long xor = 0L;

        while(M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());

            if(query <= 2) {
                long x = Long.parseLong(st.nextToken());
                xor ^= x;

                if(query == 1){
                    sum += x;
                    A.merge(x, 1, Integer::sum);
                }
                else{
                    sum -= x;
                    A.merge(x, -1, Integer::sum);
                }
            }
            else {
                if(query == 3) sb.append(sum).append(NEW_LINE);
                else sb.append(xor).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }
}

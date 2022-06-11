package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25287번: 순열 정렬
 *
 * @see https://www.acmicpc.net/problem/25287
 *
 */
public class Boj25287 {

    private static final String INC ="YES\n";
    private static final String DEC ="NO\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            int[] seq = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                seq[i] = Integer.parseInt(st.nextToken());
            }

            sb.append(change(seq));
        }

        System.out.println(sb);
    }

    private static String change(int[] seq) {
        int prev = -1;

        for(int s: seq) {
            int min = Math.min(s, seq.length - s + 1);
            int max = Math.max(s, seq.length - s + 1);

            if(min >= prev) prev = min;
            else if(max >= prev) prev = max;
            else return DEC;
        }

        return INC;
    }


}

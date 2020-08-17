package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14569번: 시간표 짜기
 *
 * @see https://www.acmicpc.net/problem/14569
 *
 */
public class boj14569 {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] subject = new long[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            while(k-- > 0) {
                subject[i] += 1L << Integer.parseInt(st.nextToken());           // contains
            }
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());

        while(M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());

            long match = 0;
            while(p-- > 0) {
                match += 1L << Integer.parseInt(st.nextToken());                // free time
            }

            int result = 0;
            for(int i = 0; i < N; i++) {
                if ((match & subject[i]) == subject[i]) result++;               // get candidate
            }

            sb.append(result).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }
}

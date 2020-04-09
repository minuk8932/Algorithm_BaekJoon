package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 9213번: 꽤 좋은 수
 *
 * @see https://www.acmicpc.net/problem/9213/
 *
 */
public class Boj9213 {
    private static int[] perfector = new int[1_000_001];

    private static final String T = "Test ";
    private static final String C = ": ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        init();

        int t = 1;

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a + b + c == 0) break;

            sb.append(T).append(t++).append(C).append(count(a, b, c)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void init() {
        for(int i = 2; i < perfector.length; i++) {
            perfector[i]++;

            for(int j = i + i; j < perfector.length; j += i) {
                if(j % i == 0) perfector[j] += i;
            }
        }
    }

    private static int count(int from, int to, int diff) {
        int count = 0;

        for(int i = from; i <= to; i++){                    // find number
            int target = Math.abs(i - perfector[i]);
            if(target <= diff) count++;
        }

        return count;
    }
}

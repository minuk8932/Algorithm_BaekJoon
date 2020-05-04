package search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 9489번: 사촌
 *
 * @see https://www.acmicpc.net/problem/9489
 *
 */
public class Boj9489 {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            if(N + K == 0) break;

            int[] elements = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                elements[i] = Integer.parseInt(st.nextToken());
            }

            sb.append(findCousin(N, K, elements)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int findCousin(int n, int k, int[] e) {
        int[] set = new int[n];
        int current = -1;
        int target = 0;

        set[0] = -1;
        for (int i = 1; i < n; i++) {
            if(e[i] == k) target = i;                   // find target

            if (e[i] - e[i - 1] != 1) current++;
            set[i] = current;                           // make set
        }

        int count = 0;
        if (set[target] != -1) {                        // target is root
            for (int i = 1; i < n; i++) {
                if (set[set[i]] == set[set[target]] && set[i] != set[target]) count++;      // different parent, same level
            }
        }

        return count;
    }
}

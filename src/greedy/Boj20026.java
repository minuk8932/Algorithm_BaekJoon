package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 20026번: Fix wiring
 *
 * @see https://www.acmicpc.net/problem/20026
 *
 */
public class Boj20026 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int size = (N * (N - 1)) >> 1;
        long[] tags = new long[size];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < size; i++) {
            tags[i] = Long.parseLong(st.nextToken());
        }

        System.out.println(mst(N, tags));
    }

    private static String mst(int n, long[] tags) {
        Arrays.sort(tags);

        int index = 0;
        long min = 0, max = 0;

        for (int i = 0; i < n - 1; i++) {
            min += tags[i];
            max += tags[index];
            index += i + 1;
        }

        return min + " " + max;
    }
}

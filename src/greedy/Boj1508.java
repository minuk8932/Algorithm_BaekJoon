package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1508번: 레이스
 *
 * @see https://www.acmicpc.net/problem/1508/
 *
 */
public class Boj1508 {
    private static StringBuilder sb;
    private static String res = "";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] track = new int[K];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            track[i] = Integer.parseInt(st.nextToken());
        }

        binarySearch(N, M, track);
        System.out.println(res);
    }

    private static void binarySearch(int n, int m, int[] t) {
        int start = 0;
        int end = n;

        while(start <= end) {
            int mid = (start + end) >> 1;           // find maximum interval

            boolean flag = arrangement(m, t, mid);
            if(flag) start = mid + 1;
            else end = mid - 1;
        }
    }

    private static boolean arrangement(int m, int[] t, int limit) {
        sb = new StringBuilder();

        int prev = t[0];
        int count = m - 1;
        sb.append(1);

        for(int i = 1; i < t.length; i++) {
            if(t[i] - prev < limit || count <= 0){  // already arranged
                sb.append(0);
                continue;
            }

            count--;

            prev = t[i];
            sb.append(1);
        }

        if(count <= 0) {                             // save arrangement
            res = sb.toString();
            return true;
        }

        return false;
    }
}

package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20917번: 사회적 거리두기
 *
 * @see https://www.acmicpc.net/problem/20917
 *
 */
public class Boj20917 {

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            int[] x = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                x[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(x);
            sb.append(binarySearch(x, s)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Binary Search
     *
     * line 64: seat, greeeeeeeeedy
     *
     * @param x
     * @param s
     * @return
     */
    private static int binarySearch(int[] x, int s) {
        int start = 0;
        int end = 1_000_000_000;

        int result = 0;

        while(start <= end) {
            int mid = (start + end) >> 1;

            int count = 1;
            int prev = x[0];
            for(int i = 1; i < x.length; i++) {
                if(x[i] - prev < mid) continue;

                prev = x[i];
                count++;
            }

            if(count >= s) {
                result = mid;
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }

        return result;
    }
}

package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 1477번: 휴게소 세우기
 *
 * @see https://www.acmicpc.net/problem/1477/
 *
 */
public class Boj1477 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()) + 2;
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] rest = new int[N];
        st = new StringTokenizer(br.readLine());

        rest[0] = 0;                    // each sides
        rest[1] = L;

        for(int i = 2; i < N; i++) {
            rest[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(rest);
        System.out.println(binarySearch(N, M, L, rest));
    }

    private static int binarySearch(int n, int m, int l, int[] r) {
        int start = 0, end = l;
        int result = 0;

        while (start <= end) {
            int mid = (start + end) / 2;
            int count = 0;

            for(int i = 1; i < n; i++) {                // between i ~ i+1, has enough interval compare with mid
                count += (r[i] - r[i - 1] - 1) / mid;
            }

            if(count <= m) {
                end = mid - 1;
                result = mid;
            }
            else {
                start = mid + 1;
            }
        }

        return result;
    }
}

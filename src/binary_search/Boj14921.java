package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14921번: 용액 합성하기
 *
 * @see https://www.acmicpc.net/problem/14921/
 *
 */
public class Boj14921 {
    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] liquid = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            liquid[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(binarySearch(N, liquid));
    }

    private static int binarySearch(int n, int[] liq) {
        int result = INF;

        for(int i = 0; i < n; i++) {
            int start = 0, end = n - 1;
            int target = liq[i];

            while (start <= end) {
                int mid = (start + end) / 2;
                int sum = target + liq[mid];                            // mix

                if(sum > 0) {                                           // except duplicated
                    end = mid - 1;
                    if(i == mid) continue;
                    if(Math.abs(result) > Math.abs(sum)) result = sum;
                }
                else if(sum < 0) {
                    start = mid + 1;
                    if(i == mid) continue;
                    if(Math.abs(result) > Math.abs(sum)) result = sum;
                }
                else {
                    if(i == mid) break;
                    return 0;
                }
            }
        }

        return result;
    }
}

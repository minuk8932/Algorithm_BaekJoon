package two_pointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 9728번: Pair sum
 *
 * @see https://www.acmicpc.net/problem/9728
 *
 */
public class Boj9728 {

    private static final String NEW_LINE = "\n";
    private static final String CASE = "Case #";
    private static final String COLON = ": ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            sb.append(CASE).append(t).append(COLON).append(pairSum(M, arr)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int pairSum(int target, int[] arr) {
        int result = 0;

        int r = arr.length - 1;
        for(int l = 0; l < r; l++) {
            while (arr[l] + arr[r] > target) r--;
            if(arr[l] + arr[r] == target) result++;
        }

        return result;
    }
}
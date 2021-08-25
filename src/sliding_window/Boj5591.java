package sliding_window;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 5591번: 最大の和
 *
 * @see https://www.acmicpc.net/problem/5591
 *
 */
public class Boj5591 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = PARSE.apply(st.nextToken());
        int k = PARSE.apply(st.nextToken());

        int[] arr = ARRAY.apply(n);
        for(int i = 0; i < n; i++) {
            arr[i] = PARSE.apply(br.readLine());
        }

        System.out.println(slidingWindow(arr, k));

    }

    private static int slidingWindow(int[] arr, int size) {
        int max = 0;

        for(int i = 0; i < size; i++) {
            max += arr[i];
        }

        int sum = max;

        for(int i = size; i < arr.length; i++) {
            sum += arr[i] - arr[i - size];
            max = Math.max(sum, max);
        }

        return max;
    }

    private static final Function<String, Integer> PARSE = Integer::parseInt;
    private static final Function<Integer, int[]> ARRAY = int[]::new;

}

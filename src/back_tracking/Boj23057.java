package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 23057번: 도전 숫자왕
 *
 * @see https://www.acmicpc.net/problem/23057
 *
 */
public class Boj23057 {

    private static Set<Integer> visit = new HashSet<>();
    private static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = PARSE.apply(br.readLine());
        int total = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = ARRY.apply(N);
        for(int i = 0; i < arr.length; i++) {
            arr[i] = PARSE.apply(st.nextToken());
            total += arr[i];
        }

        recursion(N - 1, total);
        System.out.println(total - visit.size() + 1);
    }

    private static void recursion(int current, int sum) {
        visit.add(sum);
        if (current < 0) return;

        recursion(current - 1, sum - arr[current]);
        recursion(current - 1, sum);
    }

    private static final Function<String, Integer> PARSE = Integer::parseInt;
    private static final Function<Integer, int[]> ARRY = int[]::new;

}

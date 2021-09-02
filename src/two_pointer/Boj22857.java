package two_pointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 22857번: 가장 긴 연속한 짝수 부분 수열 (small)
 *
 * @see https://www.acmicpc.net/problem/22857
 *
 */
public class Boj22857 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = PARSE.apply(st.nextToken());
        int K = PARSE.apply(st.nextToken());

        int[] value = ARR.apply(N);
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            value[i] = PARSE.apply(st.nextToken());
        }

        System.out.println(sweep(value, K));
    }

    private static int sweep(int[] val, int k) {
        Queue<Integer> q = new ArrayDeque<>();
        int max = 0;
        int size = 0;
        int odd = 0;

        for(int v: val) {
            q.offer(v);

            size++;
            odd += v % 2;

            if(odd <= k) {
                max = Math.max(size - odd, max);
            }
            else {
                while(odd > k) {
                    int current = q.poll();
                    odd -= current % 2;
                    size--;
                }
            }
        }

        return max;
    }

    private static final Function<String, Integer> PARSE = Integer::parseInt;
    private static final Function<Integer, int[]> ARR = int[]::new;
}

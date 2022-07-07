package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 3151번: 합이 0
 *
 * @see https://www.acmicpc.net/problem/3151
 *
 */
public class Boj3151 {

    private static int[] consumer = new int[40_001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);
        System.out.println(teamMatching(A));
    }

    private static long teamMatching(int[] a) {
        long answer = 0;
        int half = consumer.length >> 1;

        for (int i = 0; i < a.length; i++) {
            answer += consumer[half - a[i]];

            for (int j = 0; j < i; j++) {
                consumer[a[i] + a[j] + half]++;
            }
        }

        return answer;
    }
}

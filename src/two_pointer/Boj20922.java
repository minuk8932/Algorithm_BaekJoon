package two_pointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20922번: 겹치는 건 싫어
 *
 * @see https://www.acmicpc.net/problem/20922
 *
 */
public class Boj20922 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] a = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(twoPointer(K, a));
    }

    private static int twoPointer(int k, int[] arr) {
        Queue<Integer> q = new ArrayDeque<>();
        int[] radix = new int[100_001];
        int max = 0;

        for(int a: arr) {
            max = Math.max(max, q.size());

            while (radix[a] == k) {
                radix[q.poll()]--;
            }

            q.offer(a);
            radix[a]++;
        }

        return Math.max(max, q.size());
    }
}

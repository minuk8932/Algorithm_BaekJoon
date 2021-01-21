package deque;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20301번: 반전 요세푸스
 *
 * @see https://www.acmicpc.net/problem/20301
 *
 */
public class Boj20301 {

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        System.out.println(josephus(N, K, M));
    }

    private static String josephus(int n, int k, int m) {
        Deque<Integer> deq = new LinkedList<>();
        for(int i = 1; i <= n; i++) {
            deq.offer(i);
        }

        boolean rev = false;
        int count = 0;

        StringBuilder sb = new StringBuilder();
        while (!deq.isEmpty()) {
            int loop = k - 1;

            while (loop-- > 0) {
                if(rev) deq.offerFirst(deq.pollLast());
                else deq.offerLast(deq.pollFirst());
            }

            sb.append(rev ? deq.pollLast(): deq.pollFirst()).append(NEW_LINE);

            if (m == ++count){
                rev = !rev;
                count %= m;
            }
        }

        return sb.toString();
    }
}

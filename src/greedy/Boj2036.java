package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 *
 * @author exponential-e
 * 백준 2036번: 수열의 점수
 *
 * @see https://www.acmicpc.net/problem/2036/
 *
 */
public class Boj2036 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer>[] pq = new PriorityQueue[2];
        pq[0] = new PriorityQueue<>();
        pq[1] = new PriorityQueue<>();

        for(int i = 0; i < N; i++) {
            int val = Integer.parseInt(br.readLine());
            if(val > 0) pq[0].offer(-val);
            else pq[1].offer(val);
        }

        System.out.println(remove(pq[0], true) + remove(pq[1], false));
    }

    private static long remove(PriorityQueue<Integer> pq, boolean flag) {
        long result = 0;

        while(!pq.isEmpty()) {
            long one = pq.poll();
            if(pq.isEmpty()) return (flag ? -one: one) + result;

            long two = pq.poll();
            if(flag && (one == -1 || two == -1)) result += -(one + two);    // positive 1, then add
            else result += (one * two);
        }

        return result;
    }
}

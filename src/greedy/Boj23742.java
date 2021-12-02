package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 23742번: Player-based Team Distribution
 *
 * @see https://www.acmicpc.net/problem/23742
 *
 */
public class Boj23742 {

    private static Queue<Long> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>(Comparator.comparingLong(x -> -x));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            pq.offer(Long.parseLong(st.nextToken()));
        }

        System.out.println(process());
    }

    /**
     *
     * Process
     *
     * line 49 ~ 52: is negative
     *
     * @return
     */
    private static long process() {
        long answer = 0;
        long count = 0;
        long players = 0;

        while(!pq.isEmpty()) {
            long current = pq.poll();

            if(current * count + players < 0){
                answer += current;
                continue;
            }

            players += current;
            count++;
        }

        return answer + players * count;
    }
}

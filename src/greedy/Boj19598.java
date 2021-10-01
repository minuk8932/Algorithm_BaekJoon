package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 19598번: 최소 회의실 개수
 *
 * @see https://www.acmicpc.net/problem/19598
 *
 */
public class Boj19598 {

    private static class Conference{
        int time;
        int seq;

        public Conference(int time, int seq) {
            this.time = time;
            this.seq = seq;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Conference> pq = new PriorityQueue<>((c1, c2) -> {
            if(c1.time == c2.time) return c1.seq - c2.seq;
            return c1.time - c2.time;
        });

        while(N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            pq.offer(new Conference(start, 1));
            pq.offer(new Conference(end, -1));
        }

        int times = 0;
        int max = 0;

        while(!pq.isEmpty()) {
            times += pq.poll().seq;
            max = Math.max(times, max);
        }

        System.out.println(max);
    }
}

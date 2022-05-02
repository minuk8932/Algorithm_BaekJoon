package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25045번: 비즈 마켓
 *
 * @see https://www.acmicpc.net/problem/25045
 *
 */
public class Boj25045 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Queue<Long> merchandise = dataOffer(N, br.readLine(), -1);
        Queue<Long> cost = dataOffer(M, br.readLine(), 1);

        System.out.println(satisfyDegree(merchandise, cost));
    }

    private static long satisfyDegree(Queue<Long> merchandise, Queue<Long> cost) {
        long answer = 0;

        while(!merchandise.isEmpty() && !cost.isEmpty()) {
            long satisfy = -merchandise.poll() - cost.poll();
            if(satisfy < 0) continue;

            answer += satisfy;
        }

        return answer;
    }


    private static Queue<Long> dataOffer(int size, String input, int sort) {
        StringTokenizer st = new StringTokenizer(input);
        Queue<Long> pq = new PriorityQueue<>();

        while(size-- > 0) {
            pq.offer(Long.parseLong(st.nextToken()) * sort);
        }

        return pq;
    }
}

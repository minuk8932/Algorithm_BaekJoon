package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 23559번: 밥
 *
 * @see https://www.acmicpc.net.problem/23559
 *
 */
public class Boj23559 {

    private static final int THOUSAND = 1_000;
    private static final int THOUSAND4 = 4_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(x -> -x));
        int acc = 0;

        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            acc += B;
            pq.offer(A - B);
        }

        System.out.println(process(X - pq.size() * THOUSAND, pq) + acc);
    }

    private static int process(int x, PriorityQueue<Integer> pq) {
        int cost = 0;

        while(!pq.isEmpty()) {
            int current = pq.poll();
            if (x < THOUSAND4 || current <= 0) break;

            x -= THOUSAND4;
            cost += current;
        }

        return cost;
    }
}

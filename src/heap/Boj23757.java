package heap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 23757번: 아이들과 선물 상자
 *
 * @see https://www.acmicpc.net/problem/23757
 *
 */
public class Boj23757 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Queue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(x -> -x));
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(st.nextToken()));
        }

        System.out.println(distributeGift(M, br.readLine(), pq));
    }

    private static int distributeGift(int m, String studentsInput, Queue<Integer> pq) {
        StringTokenizer st = new StringTokenizer(studentsInput);

        while(m-- > 0) {
            int wanted = Integer.parseInt(st.nextToken());
            if(pq.isEmpty()) return 0;

            int current = pq.poll();
            current -= wanted;

            if(current < 0) return 0;
            if(current == 0) continue;

            pq.offer(current);
        }

        return 1;
    }
}

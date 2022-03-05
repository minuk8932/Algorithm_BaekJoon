package greedy;

import common.Node;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2141번: 우체국
 *
 * @see https://www.acmicpc.net/problem/2141
 *
 */
public class Boj2141 {

    private static PriorityQueue<Node<Integer, Long>> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long sum = 0;
        pq = new PriorityQueue<>(Comparator.comparingLong(Node<Integer, Long>::getNode));

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int candidate = Integer.parseInt(st.nextToken());
            long people = Long.parseLong(st.nextToken());

            pq.offer(new Node.Builder(candidate).cost(people).build());
            sum += people;
        }

        System.out.println(constructPostOffice(sum));
    }

    /**
     *
     * Construct postoffice
     *
     * line 59: min separated people count
     * line 60: calculate separated people each village
     *
     * @param totalPeople
     * @return
     */
    private static long constructPostOffice(long totalPeople) {
        int answer = 0;
        long accumulate = 0;

        while(!pq.isEmpty()) {
            Node<Integer, Long> current = pq.poll();

            if (accumulate < totalPeople - accumulate) answer = current.getNode();
            accumulate += current.getCost();
        }

        return answer;
    }
}

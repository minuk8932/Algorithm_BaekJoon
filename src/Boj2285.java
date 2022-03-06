import common.Node;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2285번: 우체국
 *
 * @see https://www.acmicpc.net/problem/2285
 *
 */
public class Boj2285 {

    private static PriorityQueue<Node<Integer, Long>> villages;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        villages = new PriorityQueue<>(Comparator.comparingInt(Node::getNode));
        long sum = 0;
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            long A = Long.parseLong(st.nextToken());

            villages.offer(new Node.Builder(X).cost(A).build());
            sum += A;
        }

        System.out.println(postOfficeConstruction(sum));
    }

    /**
     *
     * PostOffice construction
     *
     * line 55: find village that people lives in more than half.
     *
     * @param totalPeople
     * @return
     */
    private static int postOfficeConstruction(long totalPeople) {
        long accumulate = 0L;
        int answer = 0;

        while(!villages.isEmpty()) {
            Node<Integer, Long> current = villages.poll();

            if ((accumulate << 1) < totalPeople) answer = current.getNode();
            accumulate += current.getCost();
        }

        return answer;
    }
}

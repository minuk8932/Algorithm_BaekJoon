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
 * 백준 17509번: And the Winner Is... Ourselves!
 *
 * @see https://www.acmicpc.net/problem/17509
 *
 */
public class Boj17509 {

    private static PriorityQueue<Node<Integer, Integer>> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pq = new PriorityQueue<>(Comparator.comparingInt(Node::getNode));

        int loop = 11;
        while(loop-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            pq.offer(new Node.Builder(D).cost(V).build());
        }

        System.out.println(solved());
    }

    private static int solved() {
        int times = 0;
        int answer = 0;

        while(!pq.isEmpty()) {
            Node<Integer, Integer> current = pq.poll();
            times += current.getNode();
            answer += times + current.getCost() * 20;
        }

        return answer;
    }
}

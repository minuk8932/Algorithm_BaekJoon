package sort;

import common.Node;
import java.io.*;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 25325번: 학생 인기도 측정
 *
 * @see https://www.acmicpc.net/problem/25325
 *
 */
public class Boj25325 {

    private static final HashMap<String, Integer> STUDENTS = new HashMap<>();
    private static final PriorityQueue<Node<String, Integer>> PQ = new PriorityQueue<>(
        Comparator.comparingInt(Node<String, Integer>::getCost)
            .thenComparing(Node::getNode)
    );

    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) {
            STUDENTS.put(st.nextToken(), 0);
        }

        while(n-- > 0) {
            st = new StringTokenizer(br.readLine());

            while(st.hasMoreTokens()) {
                STUDENTS.merge(st.nextToken(), 1, Integer::sum);
            }
        }

        assembled();
        System.out.println(printing());
    }

    private static String printing() {
        StringBuilder sb = new StringBuilder();

        while(!PQ.isEmpty()) {
            Node<String, Integer> current = PQ.poll();
            sb.append(current.getNode()).append(SPACE).append(-current.getCost()).append(NEW_LINE);
        }

        return sb.toString();
    }

    private static void assembled() {
        for (Map.Entry<String, Integer> entry: STUDENTS.entrySet()) {
            PQ.offer(new Node.Builder<String, Integer>(entry.getKey())
                .cost(-entry.getValue())
                .build());
        }
    }
}

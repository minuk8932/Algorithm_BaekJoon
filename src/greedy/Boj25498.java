package greedy;

import common.Node;
import java.io.*;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 25498번: 핸들 뭘로 하지
 *
 * @see https://www.acmicpc.net/problem/25498
 *
 */
public class Boj25498 {

    private static final char EMPTY = ' ';
    private static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        char[] handle = new char[N];
        String input = br.readLine();
        for (int i = 0; i < N; i++) {
            handle[i] = input.charAt(i);
            tree.add(i, new ArrayList<>());
        }

        int loop = N - 1;
        while (loop-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        System.out.println(bfs(N, handle));
    }

    private static String bfs(int n, char[] handle) {
        StringBuilder sb = new StringBuilder();
        boolean[] visit = new boolean[n];

        Queue<Node<Integer, Character>> q = new ArrayDeque<>();
        q.offer(new Node.Builder<Integer, Character>(0).cost(handle[0]).build());
        sb.append(handle[0]);

        visit[0] = true;

        while (!q.isEmpty()) {
            int size = q.size();

            PriorityQueue<Node<Integer, Integer>> pq = new PriorityQueue<>(
                Comparator.comparingInt(Node::getCost)
            );

            while(size-- > 0) {
                Node<Integer, Character> current = q.poll();

                for (int next : tree.get(current.getNode())) {
                    if (visit[next]) continue;
                    visit[next] = true;

                    pq.offer(new Node.Builder<Integer, Integer>(next).cost(-handle[next]).build());
                }
            }

            if(pq.isEmpty()) continue;
            Node<Integer, Integer> target = pq.peek();
            char alpha = EMPTY;

            while(!pq.isEmpty()) {
                Node<Integer, Integer> current = pq.poll();
                if(current.getCost() != target.getCost()) break;

                alpha = (char) -target.getCost();
                q.offer(new Node.Builder<Integer, Character>(current.getNode()).cost(alpha).build());

                target = current;
            }

            sb.append(alpha);
        }

        return sb.toString();
    }
}

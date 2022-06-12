package breadth_first_search;

import common.Node;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 19581번: 트리의 두번째 지름
 *
 * @see https://www.acmicpc.net/problem/19581
 *
 */
public class Boj19581 {

    private static ArrayList<ArrayList<Node<Integer, Integer>>> tree = new ArrayList<>();
    private static int[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        visit = new int[N];
        for(int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }

        int loop = N - 1;
        while(loop-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            tree.get(node1).add(new Node.Builder(node2).cost(cost).build());
            tree.get(node2).add(new Node.Builder(node1).cost(cost).build());
        }

        int head = bfs(0);
        int tail = bfs(head);
        int answer = getSecond();
        bfs(tail);
        System.out.println(Math.max(answer, getSecond()));
    }

    private static int getSecond() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int v: visit) {
            pq.offer(-v);
        }

        pq.poll();
        return -pq.poll();
    }

    private static int bfs(int start) {
        Arrays.fill(visit, -1);

        Queue<Node<Integer, Integer>> q = new ArrayDeque<>();
        q.offer(new Node.Builder(start).cost(0).build());

        visit[start] = 0;

        while(!q.isEmpty()) {
            Node<Integer, Integer> current = q.poll();

            for(Node<Integer, Integer> next: tree.get(current.getNode())) {
                if(visit[next.getNode()] != -1) continue;
                visit[next.getNode()] = visit[current.getNode()] + next.getCost();

                q.offer(new Node.Builder(next.getNode()).cost(visit[next.getNode()]).build());
            }
        }

        int result = 0;
        int max = 0;
        for(int i = 0; i < visit.length; i++) {
            if(visit[i] <= max) continue;
            max = visit[i];
            result = i;
        }

        return result;
    }
}

package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 1595번: 북쪽나라의 도로
 *
 * @see https://www.acmicpc.net/problem/1595/
 *
 */
public class Boj1595 {
    private static ArrayList<Node>[] tree = new ArrayList[10_000];
    private static long[] dist = new long[tree.length];

    private static class Node {
        int node;
        long cost;

        public Node(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }

        while (true) {
            int a, b;
            long c;

            try {
                StringTokenizer st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken()) - 1;
                b = Integer.parseInt(st.nextToken()) - 1;
                c = Long.parseLong(st.nextToken());
            }
            catch (Exception e) {
                break;
            }

            tree[a].add(new Node(b, c));
            tree[b].add(new Node(a, c));
        }

        int start = (int) findFarthest(0, false);           // from anonymous find the farthest
        System.out.println(findFarthest(start, true));            // find tree's diameter
    }

    private static long findFarthest(int start, boolean flag) {
        Arrays.fill(dist, -1);

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(start, 0));
        dist[start] = 0;

        while(!q.isEmpty()) {
            Node current = q.poll();

            for(Node next: tree[current.node]) {
                if(dist[next.node] != -1) continue;
                dist[next.node] = dist[current.node] + next.cost;

                q.offer(new Node(next.node, dist[next.node]));
            }
        }

        int index = 0;
        long max = 0;

        for(int i = 0; i < dist.length; i++){
            if(max < dist[i]) {
                index = i;
                max = dist[i];
            }
        }

        if(flag) return max;
        else return index;
    }
}

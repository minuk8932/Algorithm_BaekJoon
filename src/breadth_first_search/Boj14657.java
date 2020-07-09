package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 14657번: 준오는 최종인재야!
 *
 * @see https://www.acmicpc.net/problem/14657/
 *
 */
public class Boj14657 {
    private static ArrayList<Node>[] path;
    private static int[] visit;

    private static int min, diameter;
    private static int target;

    private static class Node {
        int node;
        int cost;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        path = new ArrayList[N];
        visit = new int[N];
        for(int i = 0; i < N; i++) {
            path[i] = new ArrayList<>();
        }

        int loop = N - 1;
        while(loop-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            int C = Integer.parseInt(st.nextToken());

            path[A].add(new Node(B, C));
            path[B].add(new Node(A, C));
        }

        diameter = visit[0] = 1;
        recursion(0, 0);            // find terminal node

        min = 0;
        visit = new int[N];
        diameter = visit[target] = 1;
        recursion(target, 0);               // get diameter

        System.out.println((min / T) + (min % T != 0 ? 1: 0));
    }

    private static void recursion(int current, int cost) {
        if (diameter <= visit[current]) {
            if(diameter == visit[current]){
                if(min > cost) {
                    target = current;
                    min = cost;
                }
            }
            else {
                target = current;
                diameter = visit[current];
                min = cost;
            }
        }

        for (Node next: path[current]) {
            if (visit[next.node] != 0) continue;
            visit[next.node] = visit[current] + 1;

            recursion(next.node, cost + next.cost);
        }
    }
}

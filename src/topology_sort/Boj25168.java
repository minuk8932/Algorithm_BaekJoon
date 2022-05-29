package topology_sort;

import common.Node;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25168번: 게으른 아리를 위한 접종 계획
 *
 * @see https://www.acmicpc.net/problem/25168
 *
 */
public class Boj25168 {

    private static int[] indegree;
    private static ArrayList<ArrayList<Node<Integer, Integer>>> path = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        indegree = new int[N];
        for(int i = 0; i < N; i++) {
            path.add(new ArrayList<>());
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken()) - 1;
            int E = Integer.parseInt(st.nextToken()) - 1;
            int W = Integer.parseInt(st.nextToken());

            indegree[E]++;
            path.get(S).add(new Node.Builder(E).cost(W).build());
        }

        System.out.println(topologySort(N));
    }

    private static int topologySort(int n) {
        Queue<Node<Integer, Integer>> q = new ArrayDeque<>();
        int[] visit = new int[n];
        int answer = 0;

        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] != 0) continue;
            q.offer(new Node.Builder(i).cost(1).build());
            visit[i] = 1;
        }

        while(!q.isEmpty()) {
            Node<Integer, Integer> current = q.poll();

            for (Node<Integer, Integer> next : path.get(current.getNode())) {
                indegree[next.getNode()]--;

                if(visit[next.getNode()] < visit[current.getNode()] + next.getCost()
                    + (next.getCost() >= 7 ? 1: 0)) {
                    visit[next.getNode()] = visit[current.getNode()] + next.getCost()
                        + (next.getCost() >= 7 ? 1: 0);

                    answer = Math.max(visit[next.getNode()], answer);
                }

                if (indegree[next.getNode()] != 0) continue;
                q.offer(new Node.Builder(next.getNode()).cost(visit[next.getNode()]).build());
            }
        }

        return answer;
    }
}

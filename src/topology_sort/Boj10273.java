package topology_sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 10273번: 고대 동굴 탐사
 *
 * @see https://www.acmicpc.net/problem/
 *
 */
public class Boj10273 {
    private static ArrayList<Node>[] path;
    private static ArrayList<Node>[] rev;
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    private static int[] cost;
    private static int[] value;
    private static int[] indegree;

    private static class Node{
        int node;
        int cost;

        public Node(int node, int cost){
            this.node = node;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            cost = new int[N];
            value = new int[N];
            path = new ArrayList[N];
            rev = new ArrayList[N];
            indegree = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                value[i] = Integer.parseInt(st.nextToken());
                path[i] = new ArrayList<>();
                rev[i] = new ArrayList<>();
            }

            while(E-- > 0){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                int cost = Integer.parseInt(st.nextToken());

                path[from].add(new Node(to, cost));             // make path
                indegree[to]++;                                 // indegree
            }

            sb.append(topologySort(N));
        }

        System.out.print(sb.toString());
    }

    private static String topologySort(int n){
        Arrays.fill(cost, Integer.MIN_VALUE);
        cost[0] = value[0];

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, value[0]));

        while(!q.isEmpty()) {
            Node current = q.poll();

            for(Node next: path[current.node]) {
                indegree[next.node]--;

                if(cost[next.node] < value[next.node] - next.cost + cost[current.node]) {       // topology sort
                    cost[next.node] = value[next.node] - next.cost + cost[current.node];
                    rev[next.node].add(new Node(current.node, next.cost));
                }

                if(indegree[next.node] == 0) q.offer(next);
            }
        }

        StringBuilder sb = new StringBuilder();

        int max = Integer.MIN_VALUE;
        int idx = 0;
        for(int i = 0; i < n; i++){
            if(cost[i] > max){
                max = cost[i];
                idx = i;
            }
        }

        sb.append(max).append(SPACE);
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        Queue<Node> backQ = new LinkedList<>();
        backQ.add(new Node(idx, max));
        stack.push(idx + 1);

        while(!backQ.isEmpty()){
            Node current = backQ.poll();

            for(Node prev: rev[current.node]){
                if(cost[prev.node] == cost[current.node] - value[current.node] + prev.cost){        // is right path?
                    stack.push(prev.node + 1);                                                   // make path
                    backQ.offer(new Node(prev.node, cost[prev.node]));
                }
            }
        }

        sb.append(stack.size()).append(NEW_LINE);

        while(!stack.isEmpty()){
            int p = stack.pop();
            sb.append(p).append(SPACE);
        }

        sb.append(NEW_LINE);
        return sb.toString();
    }
}

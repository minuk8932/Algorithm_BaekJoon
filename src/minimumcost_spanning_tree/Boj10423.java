package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 10423번: 전기가 부족해
 *
 * @see https://www.acmicpc.net/problem/10423/
 *
 */
public class Boj10423 {
    private static PriorityQueue<Node> pq = new PriorityQueue<>();
    private static int[] parent;

    private static class Node implements Comparable<Node>{
        int node1;
        int node2;
        int cost;

        public Node(int node1, int node2, int cost){
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost < n.cost ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        while(K-- > 0){
            int start = Integer.parseInt(st.nextToken()) - 1;
            pq.offer(new Node(N, start, 0));
        }

        init(N);

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            pq.offer(new Node(u, v, w));
        }

        System.out.println(kruskal());
    }

    private static void init(int n){
        parent = new int[n + 1];                    // make spare node
        for(int i = 0; i < parent.length; i++){
            parent[i] = -1;
        }
    }

    private static int find(int x){
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merged(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y) return true;

        if(parent[x] < parent[y]){
            parent[x] += parent[y];
            parent[y] = x;
        }
        else{
            parent[y] += parent[x];
            parent[x] = y;
        }

        return false;
    }

    private static long kruskal(){
        long cost = 0;

        while(!pq.isEmpty()){
            Node current = pq.poll();

            if(merged(current.node1, current.node2)) continue;      // merging
            cost += current.cost;
        }

        return cost;
    }
}

package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 11085번: 군사 이동
 *
 * @see https://www.acmicpc.net/problem/11085/
 *
 */
public class Boj11085 {
    private static int[] parent;
    private static PriorityQueue<Node> pq = new PriorityQueue<>();

    private static class Node implements Comparable<Node> {
        int node1;
        int node2;
        int cost;

        public Node(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost > n.cost ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        parent = new int[p];
        for(int i = 0; i < p; i++){
            parent[i] = -1;
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        while(w-- > 0){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

           pq.offer(new Node(node1, node2, cost));
        }

        System.out.println(kruskal(s, e));
    }

    private static int find (int x){
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merged (int x, int y){
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

    private static int kruskal(int start, int end){
        int result = Integer.MAX_VALUE;

        while(!pq.isEmpty()){
            Node current = pq.poll();

            if(merged(current.node1, current.node2)) continue;
            result = Math.min(result, current.cost);                // find narrow size

            if(find(start) == find(end)) break;                     // can attack
        }

        return result;
    }
}

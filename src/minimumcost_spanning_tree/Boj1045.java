package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1045번: 도로
 *
 * @see https://www.acmicpc.net/problem/1045/
 *
 */
public class Boj1045 {
    private static ArrayList<Node> list = new ArrayList<>();
    private static int[] parent;

    private static final String SPACE = " ";

    private static class Node implements Comparable<Node>{
        int x;
        int y;

        public Node(int x, int y ){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node n) {
            return this.x < n.x ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            String line = br.readLine();

            for(int j = i + 1; j < N; j++){
                if(line.charAt(j) == 'N') continue;
                list.add(new Node(i, j));
            }
        }

        init(N);
        System.out.println(kruskal(N, M));
    }

    private static void init(int n){
        parent = new int[n];
        Collections.sort(list);

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

    private static String kruskal(int n, int m){
        int size = 0;
        int len = list.size();

        boolean[] visit = new boolean[len];
        int[] count = new int[n];

        for(int i = 0; i < len; i++){
            Node node = list.get(i);
            if(merged(node.x, node.y)) continue;
            if(size >= n - 1) break;

            count[node.x]++;
            count[node.y]++;
            size++;
            visit[i] = true;
        }

        if(size < n - 1) return "-1";           // if not mst

        for(int i = 0; i < len; i++) {
            Node node = list.get(i);
            if (size >= m) break;
            if (visit[i]) continue;

            count[node.x]++;
            count[node.y]++;
            size++;
            visit[i] = true;
        }

        if(size < m) return "-1";               // another is not mst

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++){
            sb.append(count[i]).append(SPACE);
        }

        return sb.toString();
    }
}

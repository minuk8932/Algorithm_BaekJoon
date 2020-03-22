package depth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 18267번: Mlik Visits
 *
 * @see https://www.acmicpc.net/problem/18267/
 *
 */
public class Boj18267 {
    private static ArrayList<Integer>[] tree;
    private static int[] visit;
    private static int[] data;
    private static int N;
    private static int count;

    private static final char GUERNSEY = 'G';

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init(br.readLine().toCharArray());

        int loop = N - 1;
        while(loop-- > 0){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        for(int i = 0; i < N; i++){
            if(visit[i] != 0) continue;
            count++;
            dfs(i);
        }

        StringBuilder sb = new StringBuilder();

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int cow = st.nextToken().charAt(0) == GUERNSEY ? 1: 2;

            if (data[node1] == cow || visit[node1] != visit[node2]) sb.append(1);
            else sb.append(0);
        }

        System.out.println(sb.toString());
    }

    private static void init(char[] c){
        data = new int[N];
        visit = new int[N];
        tree = new ArrayList[N];

        for(int i = 0; i < N; i++){
            if(c[i] == GUERNSEY) data[i] = 1;
            else data[i] = 2;

            tree[i] = new ArrayList<>();
        }
    }

    private static void dfs(int current){
        if(visit[current] != 0) return;
        visit[current] = count;             // make sequence, sorting with same milk

        for(int next: tree[current]){
            if(data[current] != data[next]) continue;
            dfs(next);
        }
    }
}

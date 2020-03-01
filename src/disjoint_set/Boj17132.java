package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17132번: 두더지가 정보섬에 올라온 이유
 *
 * @see https://www.acmicpc.net/problem/17132/
 *
 */
public class Boj17132 {
    private static PriorityQueue<Data> datas = new PriorityQueue<>();
    private static int[] parent;

    private static class Data implements Comparable<Data>{
        int from;
        int to;
        long cost;

        public Data(int from, int to, long cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Data d) {
            return this.cost > d.cost ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        init(N--);

        while(N-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int home1 = Integer.parseInt(st.nextToken()) - 1;
            int home2 = Integer.parseInt(st.nextToken()) - 1;
            long cost = Long.parseLong(st.nextToken());

            datas.offer(new Data(home1, home2, cost));
        }

        System.out.println(disjointSet());
    }

    private static void init(int n){
        parent = new int[n];

        for(int i = 0; i < n; i++){
            parent[i] = -1;
        }
    }

    private static int find(int x){
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static void merge(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y) return;

        if(parent[x] < parent[y]){
            parent[x] += parent[y];
            parent[y] = x;
        }
        else{
            parent[y] += parent[x];
            parent[x] = y;
        }
    }

    private static long disjointSet(){
        long result = 0;

        while(!datas.isEmpty()){
            Data current = datas.poll();        // sorted by cost descending order

            result += current.cost * parent[find(current.from)] * parent[find(current.to)];     // set size is home that can reach by current cost
            merge(current.from, current.to);
        }

        return result;
    }
}

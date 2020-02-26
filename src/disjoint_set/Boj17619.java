package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17619번: 개구리 점프
 *
 * @see https://www.acmicpc.net/problem/17619/
 *
 */
public class Boj17619 {
    private static Log[] logs;
    private static int[] parent;
    private static int N;

    private static final String NEW_LINE = "\n";

    private static class Coordinate{
        int x;
        int y;

        public Coordinate (int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    private static class Log implements Comparable<Log>{
        Coordinate c1;
        Coordinate c2;
        int idx;

        public Log(int idx, Coordinate c1, Coordinate c2){
            this.idx = idx;
            this.c1 = c1;
            this.c2 = c2;
        }

        @Override
        public int compareTo(Log l) {
            if(this.c1.x < l.c1.x) return -1;
            else if(this.c1.x > l.c1.x) return 1;
            else return 0;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        init();

        logs = new Log[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            logs[i] = new Log(i, new Coordinate(x1, y), new Coordinate(x2, y));
        }

        makeSet();

        StringBuilder sb = new StringBuilder();

        while(Q-- > 0){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            sb.append(find(from) == find(to) ? 1 : 0).append(NEW_LINE);     // find parent
        }

        System.out.println(sb.toString());
    }

    private static void init(){
        parent = new int[N];

        for(int i = 0; i < N; i++){
            parent[i] = -1;
        }
    }

    private static void makeSet(){
        Arrays.sort(logs);

        int x = logs[0].c2.x;
        int target = logs[0].idx;

        for(int i = 1; i < N; i++){
            if(x >= logs[i].c1.x){                  // line sweeping
                merge(logs[i].idx, target);         // is contacted? make set
                x = Math.max(x, logs[i].c2.x);
            }
            else{
                x = logs[i].c2.x;                   // another set
                target = logs[i].idx;
            }
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
}

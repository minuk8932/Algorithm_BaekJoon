package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1430번: 공격
 *
 * @see https://www.acmicpc.net/problem/1430/
 *
 */
public class Boj1430 {
    private static int N;
    private static ArrayList<Integer>[] link;

    private static class Pair{
        int x;
        int y;

        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        double R = Double.parseDouble(st.nextToken());
        double D = Double.parseDouble(st.nextToken());

        Pair[] towers = new Pair[N + 1];
        link = new ArrayList[N + 1];

        link[0] = new ArrayList<>();
        towers[0] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        for(int node1 = 1; node1 <= N; node1++){
            st = new StringTokenizer(br.readLine());
            link[node1] = new ArrayList<>();
            towers[node1] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            for(int node2 = 0; node2 < node1; node2++){                 // make graph with reached nodes
                double d = distance(towers[node1], towers[node2]);
                if(d > R) continue;

                link[node1].add(node2);
                link[node2].add(node1);
            }
        }

        System.out.println(String.format("%.2f", attack(D, towers)));
    }

    private static double attack(double deal, Pair[] t){
        boolean[] visit = new boolean[N + 1];
        double result = 0;

        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        visit[0] = true;

        double time = 1;

        while(!q.isEmpty()){
            int size = q.size();

            while(size-- > 0){                   // find similar distance nodes
                int current = q.poll();

                for(int next: link[current]){
                    if(visit[next]) continue;
                    visit[next] = true;

                    result += deal * time;      // add deal
                    q.offer(next);
                }
            }

            time /= 2;
        }


        return result;
    }

    private static double distance(Pair p1, Pair p2){
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }
}

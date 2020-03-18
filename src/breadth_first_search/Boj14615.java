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
 * 백준 14615번: Defend th CTP!!
 *
 * @see https://www.acmicpc.net/problem/14615/
 *
 */
public class Boj14615 {
    private static ArrayList<Integer>[] tube;
    private static ArrayList<Integer>[] revTube;
    private static boolean[] visit;
    private static boolean[] saved;

    private static final String DEF = "Defend the CTP\n";
    private static final String DES = "Destroyed the CTP\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        tube = new ArrayList[N];
        revTube = new ArrayList[N];
        visit = new boolean[N];
        saved = new boolean[N];
        for (int i = 0; i < N; i++) {
            tube[i] = new ArrayList<>();
            revTube[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken()) - 1;
            int Y = Integer.parseInt(st.nextToken()) - 1;
            tube[X].add(Y);
            revTube[Y].add(X);
        }

        bfs(0);
        rev(N - 1);

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            int node = Integer.parseInt(br.readLine()) - 1;
            sb.append(saved[node] && visit[node] ? DEF: DES);       // find crossed planet
        }

        System.out.println(sb.toString());
    }

    private static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();

        q.offer(start);
        visit[start] = true;

        while(!q.isEmpty()){
            int current = q.poll();

            for(int next: tube[current]){
                if(visit[next]) continue;
                visit[next] = true;

                q.offer(next);
            }
        }
    }

    private static void rev(int start){
        Queue<Integer> q = new LinkedList<>();

        q.offer(start);
        saved[start] = true;

        while(!q.isEmpty()){
            int current = q.poll();

            for(int next: revTube[current]){
                if(saved[next]) continue;
                saved[next] = true;

                q.offer(next);
            }
        }
    }
}

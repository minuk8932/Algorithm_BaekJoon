package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 18352번: 특정 도시의 최단거리 찾기
 *
 * @see https://www.acmicpc.net/problem/18352/
 *
 */
public class Boj18352 {
    private static ArrayList<Integer>[] path;
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken()) - 1;

        path = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            path[i] = new ArrayList<>();
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;

            path[A].add(B);
        }

        System.out.println(bfs(N, K, X));
    }

    private static String bfs(int n, int k, int x) {
        int[] visit = new int[n];

        Queue<Integer> q = new LinkedList<>();
        q.offer(x);
        visit[x] = 1;

        while(!q.isEmpty()){
            int current = q.poll();

            for(int next: path[current]) {
                if(visit[next] != 0) continue;
                visit[next] = visit[current] + 1;

                q.offer(next);
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < visit.length; i++) {
            if(--visit[i] != k) continue;
            result.add(i);
        }

        if(result.size() == 0) return "-1";             // any city finded
        Collections.sort(result);

        StringBuilder sb = new StringBuilder();
        for (int re: result) {
            sb.append(++re).append(NEW_LINE);
        }

        return sb.toString();
    }
}

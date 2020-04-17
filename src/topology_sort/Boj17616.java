package topology_sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 17616번: 등수 찾기
 *
 * @see https://www.acmicpc.net/problem/17616/
 *
 */
public class Boj17616 {
    private static ArrayList<Integer>[] desc;
    private static ArrayList<Integer>[] asc;

    private static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken()) - 1;

        desc = new ArrayList[N];
        asc = new ArrayList[N];
        visit = new boolean[N];

        for (int i = 0; i < N; i++) {
            desc[i] = new ArrayList<>();
            asc[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;

            desc[s].add(e);                                 // find for low rank
            asc[e].add(s);                                  // find for high rank
        }

        System.out.println(topologySort(1, X, asc) + " " + (N - topologySort(0, X, desc)));
    }

    private static long topologySort(int result, int target, ArrayList<Integer>[] tree) {
        Queue<Integer> q = new LinkedList<>();

        visit[target] = true;
        q.offer(target);

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next : tree[current]) {
                if(visit[next]) continue;
                visit[next] = true;

                result++;
                q.offer(next);
            }
        }

        return result;
    }
}

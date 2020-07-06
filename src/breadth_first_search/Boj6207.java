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
 * 백준 6207번: Cow Picnic
 *
 * @see https://www.acmicpc.net/problem/6207
 *
 */
public class Boj6207 {
    private static ArrayList<Integer> start = new ArrayList<>();
    private static ArrayList<Integer>[] path;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        while(K-- > 0) {
            start.add(Integer.parseInt(br.readLine()) - 1);
        }

        path = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            path[i] = new ArrayList<>();
        }

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            path[Integer.parseInt(st.nextToken()) - 1].add(Integer.parseInt(st.nextToken()) - 1);
        }

        System.out.println(reachablePasture(N));
    }

    private static int reachablePasture(int n) {
        int[] visit = new int[n];
        int limit = 1;

        for(int s: start) {
            Queue<Integer> q = new LinkedList<>();
            visit[s]++;

            q.offer(s);

            while(!q.isEmpty()) {
                int current = q.poll();

                for(int next: path[current]) {
                    if(visit[next] >= limit) continue;      // except redundant
                    visit[next]++;
                    q.offer(next);
                }
            }

            limit++;
        }

        int count = 0;
        int k = start.size();
        for(int v: visit) {
            if(v == k) count++;
        }

        return count;
    }
}

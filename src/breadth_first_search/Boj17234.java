package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17234번: Scoring hack
 *
 * @see https://www.acmicpc.net/problem/17234/
 *
 */
public class Boj17234 {
    private static int[][][] visit;

    private static class Pair {
        int value;
        int count;
        int visit;

        public Pair(int value, int count, int visit) {
            this.value = value;
            this.count = count;
            this.visit = visit;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        System.out.println(bfs(n, a, b) - 1);
    }

    private static int bfs(int n, int win, int lose) {              // real nogada
        int limit = n + win;
        visit = new int[limit][limit][51];

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(0, 0, 0));

        visit[0][0][0] = 1;

        while (!q.isEmpty()) {
            Pair current = q.poll();
            int[] next = {current.value * 2, current.value + win, current.value + lose};
            int nextVisit = current.visit + 1;

            if(next[1] < limit && visit[next[1]][nextVisit][current.count] == 0) {
                visit[next[1]][nextVisit][current.count] = visit[current.value][current.visit][current.count] + 1;

                if(next[1] >= n) return visit[next[1]][nextVisit][current.count];
                q.offer(new Pair(next[1], current.count, nextVisit));
            }

            if(next[2] < limit && visit[next[2]][nextVisit][current.count] == 0) {
                visit[next[2]][nextVisit][current.count] = visit[current.value][current.visit][current.count] + 1;

                if(next[2] >= n) return visit[next[2]][nextVisit][current.count];
                q.offer(new Pair(next[2], current.count, nextVisit));
            }

            if((current.count + 1) * 10 > nextVisit) continue;
            if(next[0] < limit && visit[next[0]][nextVisit][current.count + 1] == 0){
                visit[next[0]][nextVisit][current.count + 1] = visit[current.value][current.visit][current.count] + 1;

                if(next[0] >= n && next[0] < limit) return visit[next[0]][nextVisit][current.count + 1];
                q.offer(new Pair(next[0], current.count + 1, nextVisit));
            }
        }

        return 0;
    }
}

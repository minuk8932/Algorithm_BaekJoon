package breadth_first_search;

import common.Pair;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17071번: 숨바꼭질 5
 *
 * @see https://www.acmicpc.net/problem/17071
 *
 */
public class Boj17071 {

    private static final int LIMIT = 500_000;
    private static final int NO_WAY = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.println(bfs(N, K));
    }

    /**
     *
     * BFS
     *
     * line 67: get next K (position) by times
     * line 71: get min
     *
     * @param n
     * @param k
     * @return
     */
    private static int bfs(int n, int k) {
        if(n == k) return 0;

        int[][] visit = new int[2][LIMIT + 1];
        Arrays.fill(visit[0], NO_WAY);
        Arrays.fill(visit[1], NO_WAY);

        Queue<Pair<Integer>> q = new ArrayDeque<>();
        q.offer(new Pair.Builder(n, 0).build());

        while(!q.isEmpty()) {
            Pair<Integer> current = q.poll();

            if(current.getFirst() < 0 || current.getFirst() > LIMIT) continue;
            if(visit[current.getSecond() % 2][current.getFirst()] != NO_WAY) continue;
            visit[current.getSecond() % 2][current.getFirst()] = current.getSecond();

            q.offer(new Pair.Builder(current.getFirst() << 1, current.getSecond() + 1).build());
            q.offer(new Pair.Builder(current.getFirst() + 1, current.getSecond() + 1).build());
            q.offer(new Pair.Builder(current.getFirst() - 1, current.getSecond() + 1).build());
        }

        for(int time = 0; time <= LIMIT; time++) {
            int nextK = k + sigma(time);
            if (nextK > LIMIT) break;

            if(visit[time % 2][nextK] == -1) continue;
            if(visit[time % 2][nextK] > time) continue;

            return time;
        }

        return NO_WAY;
    }

    private static int sigma(int value) {
        return (value * (value + 1)) >> 1;
    }
}

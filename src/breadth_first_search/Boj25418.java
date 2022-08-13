package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25418번: 정수 a를 k로 만들기
 *
 * @see https://www.acmicpc.net/problem/25418
 *
 */
public class Boj25418 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.println(bfs(A, K));
    }

    private static int bfs(int a, int k) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(a);

        int[] visit = new int[1_000_001];
        visit[a] = 1;

        while(!q.isEmpty()) {
            int current = q.poll();

            int[] nexts = {current + 1, current << 1};
            for(int next: nexts) {
                if(next >= visit.length) continue;
                if(visit[next] != 0) continue;
                visit[next] = visit[current] + 1;

                q.offer(next);
            }
        }

        return visit[k] - 1;
    }
}

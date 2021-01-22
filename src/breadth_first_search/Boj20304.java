package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20304번: 비밀번호 제작
 *
 * @see https://www.acmicpc.net/problem/20304
 *
 */
public class Boj20304 {

    private static Queue<Integer> q = new LinkedList<>();
    private static int[] visit;
    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visit = new int[N + 1];

        int M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        while(M-- > 0) {
            int key = Integer.parseInt(st.nextToken());
            q.offer(key);
            visit[key] = 1;
        }

        System.out.println(bfs());
    }

    /**
     * line 48: find value that is just different with current value one bit -> next
     * so, visit[next] is mean that visit[current] + 1, finally we can find max distance value
     *
     * @return
     */
    private static int bfs() {
        int result = 0;

        while (!q.isEmpty()) {
            int current = q.poll();
            
            for (int idx = 1; idx <= N; idx <<= 1) {
                int next = current;

                if((current & idx) == 0) next += idx;
                else next -= idx;

                if(next > N || visit[next] != 0) continue;
                visit[next] = visit[current] + 1;

                result = Math.max(result, visit[next] - 1);
                q.add(next);
            }
        }

        return result;
    }
}

package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25376번: 이상한 스위치
 *
 * @see https://www.acmicpc.net/problem/25376
 *
 */
public class Boj25376 {

    private static final int INF = 1_000_000_000;
    private static int size;
    private static int N;
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int switches = 0;
        size = (1 << N) - 1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int A = Integer.parseInt(st.nextToken());
            switches |= (A << i);
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());

            ArrayList<Integer> link = new ArrayList<>();
            while(count-- > 0) {
                int B = Integer.parseInt(st.nextToken()) - 1;
                link.add(B);
            }

            graph.add(link);
        }

        System.out.println(bfs(switches));
    }

    private static int bfs(int switches) {
        Queue<Integer> q = new ArrayDeque<>();

        int[] visit = new int[size + 1];
        Arrays.fill(visit, INF);
        visit[switches] = 0;

        q.offer(switches);

        while(!q.isEmpty()) {
            int current = q.poll();

            for(int i = 0; i < N; i++) {
                int shift = 1 << i;
                if((current & shift) != 0) continue;

                int nextBit = current;
                nextBit ^= shift;

                for(int next: graph.get(i)) {
                    nextBit ^= (1 << next);
                }

                if(visit[nextBit] <= visit[current] + 1) continue;
                visit[nextBit] = visit[current] + 1;

                q.offer(nextBit);
            }
        }

        return visit[size] == INF ? -1: visit[size];
    }
}

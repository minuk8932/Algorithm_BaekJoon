package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author exponential-e
 * 백준 17212번: 달나라 토끼를 위한 구매대금 지불 도우미
 *
 * @see https://www.acmicpc.net/problem/17212/
 *
 */
public class Boj17212 {
    private static int[] visit;
    private static final int[] COINS = {1, 2, 5, 7};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(search(N));
    }

    private static int search(int n) {
        visit = new int[n + 1];

        Queue<Integer> q = new LinkedList<>();
        q.offer(0);

        visit[0] = 1;

        while(!q.isEmpty()){
            int current = q.poll();

            for(final int COIN: COINS) {
                int next = current + COIN;

                if(next > n) continue;
                if(visit[next] != 0) continue;
                visit[next] = visit[current] + 1;

                q.offer(next);
            }
        }

        return visit[n] - 1;
    }
}

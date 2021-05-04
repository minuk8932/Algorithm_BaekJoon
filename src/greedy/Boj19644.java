package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 19644번: 좀비 떼가 기관총 진지에도 오다니
 *
 * @see https://www.acmicpc.net/problem/19644
 *
 */
public class Boj19644 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int range = Integer.parseInt(st.nextToken());
        int deal = Integer.parseInt(st.nextToken());

        int C = Integer.parseInt(br.readLine());

        int[] zombies = new int[L + 1];
        for(int i = 1; i <= L; i++) {
            zombies[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(zombieDefense(L, range, deal, C, zombies));
    }

    /**
     *
     * Zombie defense
     *
     * line 55 ~ 60: Find zombies who killed by bomb. After in range deal is decreasing.
     * line 64 ~ 67: Decreased deal recovery
     *
     * @param l
     * @param r
     * @param d
     * @param c
     * @param zombies
     * @return
     */
    private static String zombieDefense(int l, int r, int d, int c, int[] zombies) {
        Queue<Integer> q = new LinkedList<>();
        int skip = 0;

        for(int i = 1; i <= l; i++) {
            int distance = Math.min(i, r) - skip;

            if(zombies[i] > distance * d) {
                if(c == 0) return "NO";
                c--;
                skip++;
                q.offer(i);
            }

            if(q.isEmpty()) continue;

            if(i - q.peek() == r) {
                q.poll();
                skip--;
            }
        }

        return "YES";
    }
}

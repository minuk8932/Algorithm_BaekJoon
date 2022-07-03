package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25330번: SHOW ME THE DUNGEON
 *
 * @see https://www.acmicpc.net/problem/25330
 *
 */
public class Boj25330 {

    private static int K;
    private static int answer;
    private static int[] A;
    private static int[] P;

    private static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        P = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }

        visit = new boolean[N];
        backTracking(0, 0);

        System.out.println(answer);
    }

    private static void backTracking(int current, int value) {
        answer = Math.max(answer, value);

        for (int i = 0; i < A.length; i++) {
            if (visit[i]) continue;
            visit[i] = true;

            int next = current + A[i];
            if (K >= next) {
                K -= next;
                backTracking(next, value + P[i]);
                K += next;
            }

            visit[i] = false;
        }
    }
}

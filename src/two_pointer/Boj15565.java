package two_pointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 15565번: 귀여운 라이언
 *
 * @see https://www.acmicpc.net/problem/15565
 *
 */
public class Boj15565 {

    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dolls = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            dolls[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(twoPointer(K, dolls));
    }

    /**
     *
     * Two pointer
     *
     * line 55 ~ 57: remove additional apeaches
     * line 60 ~ 63: remove first ryan
     *
     * @param k
     * @param dolls
     * @return
     */
    private static int twoPointer(int k, int[] dolls) {
        Deque<Integer> deq = new ArrayDeque<>();
        int range = INF;
        int ryans = 0;

        for(int doll: dolls) {
            deq.offer(doll);
            if(doll == 1) ryans++;

            if(ryans == k) {
                while(deq.peek() != 1) {
                    deq.poll();
                }

                range = Math.min(range, deq.size());
                if(!deq.isEmpty()){
                    deq.poll();
                    ryans--;
                }
            }
        }

        return range == INF ? -1: range;
    }
}

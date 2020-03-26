package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 9997번: 폰트
 *
 * @see https://www.acmicpc.net/problem/9997/
 *
 */
public class Boj9997 {
    private static boolean[] visit;
    private static int[] mask;
    private static int N, count;

    private static final int FONT = 67_108_863;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        mask = new int[N];
        for(int i = 0; i < N; i++){
            bitmask(i, br.readLine().toCharArray());
        }

        for(int i = 0; i < N; i++){
            visit = new boolean[N];
            backTracking(i, mask[i]);
        }

        System.out.println(count);
    }

    private static void bitmask(int idx, char[] input){
        for(char c: input){
            int loop = c - 'a';
            int val = 1;

            while(loop-- > 0) {
                val <<= 1;
            }

            mask[idx] |= val;
        }
    }

    private static void backTracking(int current, int target){
        if(target == FONT) count++;                             // accumulation
        visit[current] = true;

        for(int next = current + 1; next < N; next++){          // ignore sequence
            if(visit[next]) continue;

            backTracking(next, target | mask[next]);     // find correct sentence
            visit[next] = false;
        }
    }
}

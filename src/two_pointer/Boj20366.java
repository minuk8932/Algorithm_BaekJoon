package two_pointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20366번: 같이 눈사람 만들래?
 *
 * @see https://www.acmicpc.net/problem/20366
 *
 */
public class Boj20366 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] H = new int[N];
        for(int i = 0; i < N; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(buildSnowman(H));
    }

    /**
     *
     * Build snowman
     *
     * line 48 ~ 59: Using two pointer, compress side to side
     *
     * @param h
     * @return
     */
    private static int buildSnowman(int[] h) {
        int min = Integer.MAX_VALUE;
        Arrays.sort(h);

        for(int i = 0; i < h.length; i++) {
            for(int j = i + 1; j < h.length; j++) {
                int first = h[i] + h[j];

                int left = i;
                int right = h.length - 1;

                while(true) {
                    while(left == i || left == j) left++;
                    while(right == i || right == j) right--;

                    if(left >= right) break;
                    int second = h[left] + h[right];

                    if(first < second) right--;
                    else left++;

                    min = Math.min(min, Math.abs(first - second));
                }
            }
        }

        return min;
    }
}

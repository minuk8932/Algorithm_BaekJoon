package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 21735번: 눈덩이 굴리기
 *
 * @see https://www.acmicpc.net/problem/21735
 *
 */
public class Boj21735 {

    private static int N;
    private static int[] a;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        a = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(search(M));
    }

    /**
     *
     * Search
     *
     * line 53: snow balling cases
     *
     * @param time
     * @return
     */
    private static int search(int time) {
        int max = 1;
        int loop = 1 << time;

        for(int i = 0; i < loop; i++) {
            int[] bin = new int[time];

            String binary = Integer.toBinaryString(i);
            int len = binary.length() - 1;

            for(int j = time - 1; j >= 0; j--) {
                bin[j] = (len < 0 ? 0: binary.charAt(len) - '0') + 1;
                len--;
            }

            int index = 0;
            int ball = 1;
            for(int jump: bin) {
                index += jump;
                if(index > N) break;

                ball /= jump;
                ball += a[index];
            }

            max = Math.max(max, ball);
        }

        return max;
    }
}

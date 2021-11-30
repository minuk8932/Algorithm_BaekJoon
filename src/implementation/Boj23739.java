package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 23739번: 벼락치기
 *
 * @see https://www.acmicpc.net/contest/problem/23739
 *
 */
public class Boj23739 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] chapter = new int[N];

        for(int i = 0; i < N; i++) {
            chapter[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(study(chapter));
    }

    private static int study(int[] chaps) {
        int count = 0;
        int time = 30;

        for(int chap: chaps) {
            int half = (chap >> 1) + chap % 2;
            chap -= time;

            if(chap < 0) {
                count++;
                time = Math.abs(chap);
            }
            else {
                if(half <= time) count++;
                time = 30;
            }
        }

        return count;
    }
}

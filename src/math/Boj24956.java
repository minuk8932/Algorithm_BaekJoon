package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 24956번: 나는 정말 휘파람을 못 불어
 *
 * @see https://www.acmicpc.net/problem/24956
 *
 */
public class Boj24956 {

    private static final char[] TRACK = {'W', 'H', 'E'};
    private static final long MOD = 1_000_000_007L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();

        System.out.println(combination(br.readLine().toCharArray()));
    }

    private static long combination(char[] whistle) {
        long[] data = new long[3];
        long answer = 0;

        for(char wh: whistle) {
            if(wh == TRACK[0]){
                data[0]++;
            }
            else if(wh == TRACK[1]){
                data[1] += data[0];
            }
            else if(wh == TRACK[2]){
                answer = ((answer << 1) % MOD) + (data[2] % MOD);
                answer %= MOD;

                data[2] += data[1];
            }
        }

        return answer;
    }
}

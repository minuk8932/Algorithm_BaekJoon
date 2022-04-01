package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18310번: 안테나
 *
 * @see https://www.acmicpc.net/problem/18310
 *
 */
public class Boj18310 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] homes = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            homes[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(homes);
        System.out.println(antenna(homes));
    }

    /**
     *
     * Antenna
     *
     * line 53: add left distance, sub right distance. except exchanged pair
     *
     * @param homes
     * @return
     */
    private static int antenna(int[] homes) {
        long min = 0;
        int prev = homes[0];

        for(int i = 1; i < homes.length; i++) {
            min += homes[i] - prev;
        }

        long current = min;
        int answer = homes[0];

        for(int i = 1; i < homes.length; i++) {
            long interval = homes[i] - prev;
            current += interval * (i - 1) - interval * (homes.length - i - 1);
            prev = homes[i];

            if(current >= min) continue;
            answer = homes[i];
            min = current;
        }

        return answer;
    }
}

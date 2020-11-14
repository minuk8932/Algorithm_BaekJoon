package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20113번: 긴급 회의
 *
 * @see https://www.acmicpc.net/problem/20113
 *
 */
public class Boj20113 {
    private static HashMap<Long, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();

        StringTokenizer st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) {
            long value = Long.parseLong(st.nextToken());
            if(value == 0) continue;

            map.merge(value, 1, Integer::sum);
        }

        System.out.println(imposter());
    }

    private static String imposter() {
        int max = 0;
        for(long key: map.keySet()) {
            max = Math.max(max, map.get(key));
        }

        long target = -1;
        for(long key: map.keySet()) {
            if(map.get(key) == max) {
                if(target != -1) return "skipped";
                target = key;
            }
        }

        return target + "";
    }
}

package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17950번: 스노우 볼
 *
 * @see https://www.acmicpc.net/problem/17950/
 *
 */
public class Boj17950 {
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        long result = 0;
        long timer = x;

        while(H-- > 0){
            long h = Long.parseLong(br.readLine());
            result = ((result % MOD) + ((h % MOD) * (timer % MOD)) % MOD) % MOD;
            timer = ((timer % MOD) * (x % MOD)) % MOD;
        }

        System.out.println(result);
    }
}

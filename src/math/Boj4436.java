package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 4436번: 엘프의 검
 *
 * @see https://www.acmicpc.net/problem/4436/
 *
 */
public class Boj4436 {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = "";

        while((input = br.readLine()) != null){
            int n = Integer.parseInt(input);
            sb.append(counting(n)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static long counting(int n){
        long k = 1;
        boolean[] used = new boolean[10];
        int total = 55;

        while(true){
            long val = k * n;

            while(val > 0){
                int mod = (int) (val % 10);
                val /= 10;

                if(used[mod]) continue;
                used[mod] = true;
                total -= (mod + 1);             // check 0 ~ 9
            }

            if(total == 0) return k;
            k++;
        }
    }
}

package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 5393번: 콜라츠
 *
 * @see https://www.acmicpc.net/problem/5393/
 *
 */
public class Boj5393 {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            int N = Integer.parseInt(br.readLine());
            int fix = (N + 1) / 2;
            sb.append((N % 2 == 0 ? (N + 1) / 3: (N + 3) / 3) + fix).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }
}

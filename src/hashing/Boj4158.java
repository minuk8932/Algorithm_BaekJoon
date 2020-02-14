package hashing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 4158번: CD
 *
 * @see https://www.acmicpc.net/problem/4158/
 *
 */
public class Boj4158 {
    private static HashMap<Integer, Integer> cd;
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0) break;
            cd = new HashMap<>();

            while (N-- > 0) {
                int num = Integer.parseInt(br.readLine());
                cd.put(num, 1);
            }

            int count = 0;

            while (M-- > 0) {
                int num = Integer.parseInt(br.readLine());
                if (cd.containsKey(num)) count++;
            }

            sb.append(count).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }
}

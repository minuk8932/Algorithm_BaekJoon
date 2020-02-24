package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 10275번: 골드 러쉬
 *
 * @see https://www.acmicpc.net/problem/10275/
 *
 */
public class Boj10275 {
    private static long[] pows = new long[63];
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        init();

        while(t-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            long min = Math.min(a, b);
            int count = 0;

            for(int i = n; i >= 0; i--){
                if(min % pows[i] == 0) break;
                count++;
            }

            sb.append(count).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void init(){
        long p = 1;

        for(int i = 0; i < pows.length; i++){
            pows[i] = p;
            p *= 2;
        }
    }
}

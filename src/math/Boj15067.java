package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 15067번: Prime Driving Condition
 *
 * @see https://www.acmicpc.net/problem/15067/
 *
 */
public class Boj15067 {
    private static boolean[] prime = new boolean[10_001];
    private static boolean flag;

    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        isPrime();          // make prime

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String alpha = st.nextToken();
            int p = Integer.parseInt(st.nextToken());

            if(alpha.equals("END") && p == 0) break;

            flag = false;
            int next = getNext(p);
            String nextAlpha = (getNextAlpha(alpha));

            sb.append(nextAlpha).append(SPACE).append(next < 10 ? "000" + next:
                    next < 100 ? "00" + next: next < 1000 ? "0" + next: next + "").append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void isPrime(){
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;

        for(int i = 2; i < 101; i++){
            if(!prime[i]) continue;

            for(int j = i + i; j < prime.length; j += i){
                prime[j] = false;
            }
        }
    }

    private static int getNext(int p){
        int val = 0;

        for(int i = p; i < prime.length; i++){
            if(prime[i]){
                val = i;
                break;
            }
        }

        if(val == 0){
            val = 2;
            flag = true;
        }

        return val;
    }

    private static String getNextAlpha(String a){
        if(!flag) return a;

        char[] c = a.toCharArray();
        int carry = 0;

        for(int i = c.length - 1; i >= 0; i--){         // alpha sum
            if(c[i] == 'Z'){
                c[i] = 'A';
                carry = 1;
            }
            else{
                c[i] += 1;
                carry = 0;
            }

            if(carry == 0) break;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < c.length; i++){
            sb.append(c[i]);
        }

        return sb.toString();
    }
}

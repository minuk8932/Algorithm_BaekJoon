package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 2885번: 초콜릿 식사
 *
 * @see https://www.acmicpc.net/problem/2885/
 *
 */
public class Boj2885 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        split(K);
    }

    private static void split(int k){
        int pow = 1;

        while(k > pow){                 // find size
            pow *= 2;
        }

        int count = k == pow ? 0: 1;
        int result = pow;
        int val = k;

        pow /= 2;

        while(val != pow && pow != 0){  // get interval
            if(val > pow) val -= pow;
            count++;
            pow /= 2;
        }

        System.out.println(result + " " + (result == k ? 0: count));
    }
}

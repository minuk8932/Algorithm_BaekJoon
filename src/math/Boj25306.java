package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25306번: 연속 XOR
 *
 * @see https://www.acmicpc.net/problem/25306
 *
 */
public class Boj25306 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        System.out.println(continuousXOR(a, b));
    }

    private static long continuousXOR(long a, long b) {
        long len = b - a + 1;
        if(a % 2 == 0){
            if(b % 2 == 1) return (len >> 1) % 2;
            else return (((len - 1) >> 1) % 2) ^ b;
        }
        else {
            len--;

            if(b % 2 == 1) return ((len >> 1) % 2) ^ a;
            else return (((len - 1) >> 1) % 2) ^ a ^ b;
        }
    }
}

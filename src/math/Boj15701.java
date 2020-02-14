package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 15701번: 순서쌍
 *
 * @see https://www.acmicpc.net/problem/15701/
 *
 */
public class Boj15701 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(counting(N));
    }

    private static int counting(int n){
        int loop = (int) (Math.sqrt(n));
        int count = 2;

        for(int i = 2; i <= loop; i++){
            if(n % i == 0) count += 2;
        }

        if(loop * loop == n) count--;

        return count;
    }
}

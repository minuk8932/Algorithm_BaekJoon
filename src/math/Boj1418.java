package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 1418번: K-세준수
 *
 * @see https://www.acmicpc.net/problem/1418/
 *
 */
public class Boj1418 {
    private static boolean[] prime = new boolean[100_001];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        init();

        System.out.println(getK(N, K));
    }

    private static void init(){
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;

        int loop = (int) Math.sqrt(prime.length) + 1;

        for(int i = 2; i < loop; i++){
            if(!prime[i]) continue;

            for(int j = i + i; j < prime.length; j += i){
                prime[j] = false;
            }
        }
    }

    private static int getK(int n, int k){
        int count = 1;

        for(int val = 2; val <= n; val++){
            if(prime[val]){
                if(val <= k) count++;
                continue;
            }

            int max = 0;
            int loop = (int) Math.sqrt(val);

            for(int p = 2; p <= loop; p++){
                if(val % p == 0){
                    int p1 = val / p;

                    if (prime[p]) max = Math.max(max, p);       // candidate
                    if (prime[p1]) max = Math.max(max, p1);
                }
            }

            if(max <= k && max != 0) count++;       // find K-SJ number
        }

        return count;
    }
}

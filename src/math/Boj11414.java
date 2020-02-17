package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11414번: LCM
 *
 * @see https://www.acmicpc.net/problem/11414/
 *
 */
public class Boj11414 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        System.out.println(find(Math.abs(A - B), A, B));
    }

    private static int find(int diff, long a, int b){
        if(diff == 0) return 1;

        int loop = (int) Math.sqrt(diff) + 1;
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 1; i < loop; i++){
            if(diff % i == 0){                  // find all diff abbreviation, gcd
                int div = diff / i;
                list.add(i);

                if(i == div) continue;
                list.add(div);
            }
        }

        long candi = Long.MAX_VALUE;
        int result = Integer.MAX_VALUE;

        for(int gcd: list){
            int n = gcd - (b % gcd);            // make adder (adder < gcd)
            long lcm = (a + n) * (b + n) / gcd;
            if(lcm > candi) continue;

            result = n;
            if(lcm == candi) result = Math.min(result, n);      // min lcm, min adder
            candi = lcm;
        }

        return result;
    }
}

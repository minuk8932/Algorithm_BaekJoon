package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 25180번: 썸 팰린드롬
 *
 * @see https://www.acmicpc.net/problem/25180
 *
 */
public class Boj25180 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(makePalindrome(N));
    }

    /**
     *
     * Optimal strategy
     * - get component with even count
     * - if n is smaller than 10 then, break
     *
     * @param n
     * @return
     */
    private static int makePalindrome(int n) {
        if(n < 10) return 1;

        int total = 0;
        for(int i = 9; i > 0; i--) {
            if(n < 10) break;

            int div = n / i;
            n %= i;

            if (div % 2 != 0) {
                div--;
                n += i;
            }

            total += div;
        }

        return total + (n == 0 ? 0: 1);
    }

}

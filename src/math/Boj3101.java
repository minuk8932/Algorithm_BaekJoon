package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 3101번: 토끼의 이동
 *
 * @see https://www.acmicpc.net/problem/3101/
 *
 */
public class Boj3101 {
    private static HashMap<Character, Integer> DIRECTIONS = new HashMap<>();

    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st.nextToken();

        init();
        System.out.println(hopping(N, br.readLine().toCharArray()));
    }

    private static void init(){
        DIRECTIONS.put('D', 10);
        DIRECTIONS.put('U', -10);
        DIRECTIONS.put('R', 1);
        DIRECTIONS.put('L', -1);
    }

    private static long hopping(long n, char[] move){
        long result = 1;
        long pow = n * n;
        int row = 0, col = 0;

        for(char c: move) {
            final int DIR = DIRECTIONS.get(c);          // move
            row += DIR / 10;
            col += DIR % 10;

            long sum = row + col;
            long split = sum % 2 == 1 ? row: col;

            if(sum < n) {                               // left upper
                split++;
                result += sigma(sum);
            }
            else {                                      // right lower
                split -= (sum - n);
                result += pow - sigma(n + n - (sum + 1));
            }

            result += split;
        }

        return result;
    }

    private static long sigma(long n){
        return n * (n + 1) / 2;
    }
}

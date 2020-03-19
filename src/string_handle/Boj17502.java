package string_handle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 17502번: 클레어와 펠린드롬
 *
 * @see https://www.acmicpc.net/problem/17502/
 *
 */
public class Boj17502 {
    private static final char RAND = '?';

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        System.out.println(palindrome(br.readLine().toCharArray()));
    }

    private static String palindrome(char[] c){
        char[] result = new char[c.length];

        Arrays.fill(result, 'a');

        for(int i = 0; i < c.length / 2; i++){
            int idx = c.length - i - 1;
            if(c[i] == RAND && c[idx] == RAND) continue;

            if(c[i] != RAND && c[idx] != RAND){
                result[i] = result[idx] = c[i];
            }
            else {
                if(c[i] == RAND) result[i] = result[idx] = c[idx];
                else result[i] = result[idx] = c[i];
            }
        }

        if(c.length % 2 == 1){
            if(c[c.length / 2] != RAND) result[c.length / 2] = c[c.length / 2];     // exception!, x?ea?
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < c.length; i++){
            sb.append(result[i]);
        }

        return sb.toString();
    }
}

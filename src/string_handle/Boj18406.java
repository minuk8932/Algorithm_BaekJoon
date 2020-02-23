package string_handle;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 18406번: 럭키 스트레이트
 *
 * @see https://www.acmicpc.net/problem/18406/
 *
 */
public class Boj18406 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] N = br.readLine().toCharArray();

        System.out.println(luckyStraight(N));
    }

    private static String luckyStraight(char[] c){
        int[] val = new int[2];

        for(int i = 0; i < c.length / 2; i++){
            val[0] += (c[i] - '0');
            val[1] += (c[c.length - 1 - i] - '0');
        }

        if(val[0] == val[1]) return "LUCKY";
        return "READY";
    }
}

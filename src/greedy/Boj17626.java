package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author exponential-e
 * 백준 17626번: Four Squares
 *
 * @see https://www.acmicpc.net/problem/17626/
 *
 */
public class Boj17626 {
    private static ArrayList<Integer> pows = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(count(n));
    }

    private static int count(int n){
        findPows(n);
        int count = 4;

        for(int p1: pows){
            if(p1 == n) count = Math.min(count, 1);                     // pow 1

            for(int p2: pows){
                if(p1 + p2 == n) count = Math.min(count, 2);            // pow 2

                for(int p3: pows){
                    if(p1 + p2 + p3 == n) count = Math.min(count, 3);   // pow 3
                }
            }
        }

        return count;
    }

    private static void findPows(int n){
        int loop = (int) (Math.sqrt(n) + 1);

        for(int i = 1; i < loop; i++){          // find pows
            pows.add(i * i);
        }
    }
}

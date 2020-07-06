package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 10996번: 별 찍기 21
 *
 * @see https://www.acmicpc.net/problem/10996/
 *
 */
public class Boj10966 {
    private static final char STAR = '*';
    private static final char SPACE = ' ';
    private static final char NEW_LINE = '\n';

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(starring(N));
    }

    private static String starring(int n) {
        StringBuilder sb = new StringBuilder();
        if(n == 1) return sb.append(STAR).toString();

        int size = n * 2 - 1;

        for(int i = 0; i <= size; i++) {
            for(int j = 0; j < n; j++) {
                if(i % 2 == 0) {
                    if(j % 2 == 0) {
                        sb.append(STAR);
                    }
                    else{
                        if(j == n - 1) continue;
                        sb.append(SPACE);
                    }
                }
                else{
                    if(j % 2 == 0){
                        if(j == n - 1) continue;
                        sb.append(SPACE);
                    }
                    else{
                        sb.append(STAR);
                    }
                }
            }

            sb.append(NEW_LINE);
        }

        return sb.toString();
    }
}

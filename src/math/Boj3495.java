package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 3495번: 아스키 도형
 *
 * @see https://www.acmicpc.net/problem/3495
 *
 */
public class Boj3495 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        char[][] polygon = new char[h][w];
        for(int i = 0; i < h; i++) {
            String line = br.readLine();

            for(int j = 0; j < w; j++) {
                polygon[i][j] = line.charAt(j);
            }
        }

        System.out.println(makeSquare(polygon));
    }

    private static int makeSquare(char[][] polygon) {
        int slash = 0;
        int inner = 0;

        for(char[] poly: polygon) {
            for(char p: poly) {
                if(p == '\\' || p == '/') slash++;          // Pick's Theorem
                if(slash % 2 == 0) continue;

                if(p == '.') inner++;
            }
        }

        return slash / 2 + inner;
    }
}

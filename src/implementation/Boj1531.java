package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1531번: 투명
 *
 * @see https://www.acmicpc.net/problem/1531/
 *
 */
public class Boj1531 {
    private static int[][] picture = new int[100][100];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;

            covering(x1, y1, x2, y2);
        }

        System.out.println(counting(M));
    }

    private static void covering(int x1, int y1, int x2, int y2) {
        for(int row = x1; row <= x2; row++){
            for (int col = y1; col <= y2; col++) {
                picture[row][col]++;
            }
        }
    }

    private static int counting(int limit) {
        int count = 0;

        for(int row = 0; row < picture.length; row++) {
            for(int col = 0; col < picture[row].length; col++) {
                if(picture[row][col] > limit) count++;
            }
        }

        return count;
    }
}

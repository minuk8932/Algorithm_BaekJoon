package string_handle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20114번: 미아 노트
 *
 * @see https://www.acmicpc.net/problem/20114
 *
 */
public class Boj20114 {
    private static final char QUESTION_MARK = '?';

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        char[][] remained = new char[H][N * W];
        for(int i = 0; i < remained.length; i++) {
            String input = br.readLine();

            for(int j = 0; j < remained[i].length; j++) {
                remained[i][j] = input.charAt(j);
            }
        }

        System.out.println(noteRecovery(H, W, remained));
    }

    private static String noteRecovery(int h, int w, char[][] remained) {
        StringBuilder sb = new StringBuilder();

        for(int len = 0; len < remained[0].length; len += w) {
            sb.append(getData(h, len, w, remained));
        }

        return sb.toString();
    }

    private static char getData(int h, int len, int w, char[][] arr) {
        for(int i = 0; i < h; i++) {
            for (int j = len; j < len + w; j++) {
                if(arr[i][j] != QUESTION_MARK) return arr[i][j];        // can recovering
            }
        }

        return QUESTION_MARK;
    }
}

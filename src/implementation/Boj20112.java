package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 20112번: 사토르 마방진
 *
 * @see https://www.acmicpc.net/problem/20112
 *
 */
public class Boj20112 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        char[][] input = new char[N][N];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < N; j++) {
                input[i][j] = line.charAt(j);
            }
        }

        System.out.println(build(N, input, true).equals(build(N, input, false)) ? "YES": "NO");
    }

    private static String build(int n, char[][] arr, boolean flag) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                sb.append(flag ? arr[i][j]: arr[j][i]);
            }
        }

        return sb.toString();
    }
}

package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18883번: N M 찍기
 *
 * @see https://www.acmicpc.net/problem/18883/
 *
 */
public class Boj18883 {
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        System.out.println(print(N, M));
    }

    private static String print(int n, int m) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                sb.append(i * m + j + 1);
                if(m - 1 != j) sb.append(SPACE);
            }
            sb.append(NEW_LINE);
        }

        return sb.toString();
    }
}

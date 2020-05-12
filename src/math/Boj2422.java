package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2422번: 한윤정이 이탈리아에 가서 아이스크림을 사먹는데
 *
 * @see https://www.acmicp.net/problem/2422/
 *
 */
public class Boj2422 {
    private static boolean[][][] combination;
    private static ArrayList<Integer> except = new ArrayList<>();
    private static int CIPHER = 1_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        combination = new boolean[N][N][N];
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            except.add(a * CIPHER + b);
        }

        System.out.println(calculate(N));
    }

    private static int calculate(int n) {
        int result = n * (n - 1) * (n - 2) / 6;

        for(int e: except) {
            int a = e / CIPHER;
            int b = e % CIPHER;

            for(int c = 0; c < n; c++) {
                if(combination[a][b][c]) continue;
                if(c == a || b == c) continue;
                filling(a, b, c);

                result--;                           // find complement set
            }
        }

        return result;
    }

    private static void filling(int a, int b, int c) {
        combination[a][b][c] = combination[a][c][b] = combination[b][a][c]
                = combination[b][c][a] = combination[c][a][b] = combination[c][b][a] = true;
    }
}

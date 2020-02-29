package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14440번: 정수 수열
 *
 * @see https://www.acmicpc.net/problem/14440/
 *
 */
public class Boj14440 {
    private static final int MOD = 100;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int a1 = Integer.parseInt(st.nextToken());
        int a2 = Integer.parseInt(st.nextToken());

        System.out.println(String.format("%02d", getResult(x, y, a1, a2, st.nextToken())));
    }

    public static int[][] cartesian(int[][] matrix1, int[][] matrix2){          // find fibo with matrix
        int[][] t = new int[2][2];

        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                for(int k = 0; k < 2; k++){
                    t[i][j] += ((matrix1[i][k] % MOD) * (matrix2[k][j] % MOD)) % MOD;
                }

                t[i][j] %= MOD;
            }
        }

        return t;
    }

    private static int getResult(int x, int y, int a1, int a2, String input){
        int n = Integer.parseInt(input);

        if(n == 0) return a1;
        if(n == 1) return a2;

        int[][] result = {{1, 0}, {0, 1}};
        int[][] matrix = {{x, y}, {1, 0}};

        n--;

        while(n > 0){
            if(n % 2 == 1) result = cartesian(result, matrix);
            matrix = cartesian(matrix, matrix);

            n /= 2;
        }

        return (result[0][0] * a2 + result[0][1] * a1) % MOD;       // get result
    }
}

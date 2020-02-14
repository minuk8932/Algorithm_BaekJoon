package divide_and_conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 10830번: 행렬 제곱
 *
 * @see https://www.acmicpc.net/problem/10830/
 *
 */
public class Boj10830 {
    private static int N;

    private static final int MOD = 1_000;
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    private static HashMap<Long, int[][]> visit = new HashMap<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        int[][] matrix = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken()) % MOD;
            }
        }

        int[][] result = matrix;
        if(B != 1) result = recursion(B, matrix);

        print(result);
    }

    private  static int[][] recursion(long b, int[][] mat){
        if(b == 2){
            int[][] arr = cartesian(mat, mat);                  // conquer
            visit.put(b, arr);                                  // saving

            return arr;
        }

        if(b == 3){
            int[][] arr = cartesian(mat, cartesian(mat, mat));
            visit.put(b, arr);

            return arr;
        }

        long idx = b % 2 == 0 ? b / 2: b / 2 + 1;

        if(!visit.containsKey(b / 2)) visit.put(b / 2, recursion(b / 2, mat));      // divide
        if(!visit.containsKey(idx)) visit.put(idx, recursion(idx, mat));

        return cartesian(visit.get(b / 2), visit.get(idx));
    }

    private static void print(int[][] result){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                sb.append(result[i][j]).append(SPACE);
            }

            sb.append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int[][] cartesian(int[][] arr1, int[][] arr2){           // matrix cartesian
        int[][] make = new int[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int sum = 0;

                for(int k = 0; k < N; k++){
                    sum = ((sum % MOD) + ((arr1[i][k] % MOD) * (arr2[k][j] % MOD)) % MOD) % MOD;
                }

                make[i][j] = sum;
            }
        }

        return make;
    }
}

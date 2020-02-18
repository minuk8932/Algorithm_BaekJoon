package parsing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1388번: 바닥 장식
 *
 * @see https://www.acmicpc.net/problem/1388/
 *
 */
public class Boj1388 {
    private static boolean[][] used;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] floor = new char[N][M];
        used = new boolean[N][M];

        for(int i = 0; i < N; i++){
            String line = br.readLine();

            for(int j = 0; j < M; j++){
                floor[i][j] = line.charAt(j);
            }
        }

        System.out.println(counting(N, M, floor));
    }

    private static int counting(int n, int m, char[][] f){
        int count = 0;

        for (int i = 0; i < f.length; i++){
            for(int j = 0; j < f[i].length; j++){
                if(used[i][j]) continue;
                used[i][j] = true;

                if(f[i][j] == '-') spread(m, f, i, j + 1, true, f[i][j]);       // col
                else spread(n, f, i + 1, j, false, f[i][j]);                    // row

                count++;
            }
        }

        return count;
    }

    private static void spread(int len, char[][] f, int i, int j, boolean flag, char src){
        int target = flag ? j: i;

        while(target < len){
            char snk = flag ? f[i][target]: f[target][j];
            if(snk != src) break;

            if(flag) used[i][target++] = true;
            else used[target++][j] = true;
        }
    }
}

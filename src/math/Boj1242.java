package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1242번: 소풍
 *
 * @see https://www.acmicpc.net/problem/1242/
 *
 */
public class Boj1242 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        System.out.println(joseph(N, K, M));
    }

    private static int joseph (int n, int k, int m) {
        int result = 0;

        for (int i = 1; i <= n; i++){
            int size = n - i + 1;               // current remained

            int turn = k % size;                // except
            if(turn == 0) turn = size;

            if (m == turn){                     // his turn
                result = i;
                break;
            }

            m -= turn;                          // make sequence from 1
            if (m < 0) m += size;
        }

        return result;
    }
}

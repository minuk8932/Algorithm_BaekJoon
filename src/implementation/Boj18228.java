package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18228번: 펭귄추락대책위원회
 *
 * @see https://www.acmicpc.net/problem/18228/
 *
 */
public class Boj18228 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] ice = new int[N];
        int flag = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            ice[i] = Integer.parseInt(st.nextToken());
            if(ice[i] == -1) flag = i;
        }

        System.out.println(broken(0, flag, ice) + broken(flag + 1, N, ice));
    }

    private static int broken(int start, int end, int[] ice) {
        int result = Integer.MAX_VALUE;

        for(int i = start; i < end; i++){
            result = Math.min(result, ice[i]);
        }

        return result;
    }
}

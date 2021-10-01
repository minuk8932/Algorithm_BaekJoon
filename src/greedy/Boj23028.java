package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 23028번: 5학년은 다니기 싫어요
 *
 * @see https://www.acmicpc.net/problem/23028
 *
 */
public class Boj23028 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = 8 - Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        boolean flag = false;

        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            A += X * 3;
            B += (X + Math.min(6 - X, Y)) * 3;
            if(flag = (B >= 130 && A >= 66)) break;
        }

        System.out.println(flag ? "Nice": "Nae ga wae");
    }
}

package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18512번: 점프 점프
 *
 * @see https://www.acmicpc.net/problem/18512
 *
 */
public class Boj18512 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        int P1 = Integer.parseInt(st.nextToken());
        int P2 = Integer.parseInt(st.nextToken());

        System.out.println(meet(X, Y, P1, P2));
    }

    private static int meet (int d1, int d2, int a1, int a2) {
        HashSet<Integer> visit = new HashSet<>();
        int loop = 100;

        for(int i = 0; i <= loop; i++) {
            int x = a1 + d1 * i;
            visit.add(x);
        }

        for(int i = 0; i <= loop; i++) {
            int x = a2 + d2 * i;
            if(visit.contains(x)) return x;
        }

        return -1;
    }
}

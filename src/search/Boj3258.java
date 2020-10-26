package search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 3258번: 컴포트
 *
 * @see https://www.acmicpc.net/problem/3258
 *
 */
public class Boj3258 {
    private static boolean[] obstacle;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Z = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        obstacle = new boolean[N];
        st = new StringTokenizer(br.readLine());
        while(M-- > 0) {
            obstacle[Integer.parseInt(st.nextToken()) - 1] = true;
        }

        System.out.println(comfort(N, Z - 1));
    }

    private static int comfort(int n, int z) {
        int result = n;

        for(int hop = n - 1; hop >= 1; hop--) {
            int count = moving(0, z, hop, n);
            if (count == -1) continue;

            result = hop;
        }

        return result;
    }

    private static int moving(int idx, int z, int hop, int mod) {
        HashSet<Integer> visit = new HashSet<>();
        int jump = 0;

        while(idx != z) {
            if(obstacle[idx]) return -1;
            if(visit.contains(idx)) return -1;          // TLE
            visit.add(idx);

            idx += hop;
            if(idx >= mod) idx %= mod;

            jump++;
        }

        return jump;
    }
}

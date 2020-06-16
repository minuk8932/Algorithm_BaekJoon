package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11578번: 팀원 모집
 *
 * @see https://www.acmicpc.net/problem/11578/
 *
 */
public class Boj11578 {
    private static ArrayList<Integer>[] member;
    private static int result = 100;
    private static int N, M;

    private static boolean[] visit;
    private static int[] seq;
    private static int full;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        member = new ArrayList[M];
        for(int i = 0; i < M; i++) {
            member[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());

            while(count-- > 0 ){
                member[i].add(Integer.parseInt(st.nextToken()) - 1);
            }
        }

        full = (1 << N) - 1;

        for(int start = 0; start < M; start++) {
            visit = new boolean[M];
            seq = new int[M];

            backTracking(start, 0);
        }

        System.out.println(result == 100 ? -1: result);
    }

    private static void backTracking(int current, int count){
        seq[count] = current;                   // make sequence

        if(count == M - 1){
            int include = 0;
            int cnt = 0;

            for(int s: seq) {
                for(int value: member[s]) {
                    include |= 1 << value;
                }

                cnt++;

                if(include == full){            // all problem solved
                    result = Math.min(result, cnt);
                    return;
                }
            }

            return;
        }

        visit[current] = true;

        for (int next = 0; next < M; next++) {
            if(visit[next]) continue;

            backTracking(next, count + 1);
            visit[next] = false;
        }
    }
}

package depth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 24955번: 숫자 이어 붙이기
 *
 * @see https://www.acmicpc.net/problem/24955
 *
 */
public class Boj24955 {

    private static final long MOD = 1_000_000_007L;
    private static final String NEW_LINE = "\n";
    private static long answer;

    private static ArrayList<ArrayList<Integer>> path = new ArrayList<>();
    private static long[] doors;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        doors = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            doors[i] = Long.parseLong(st.nextToken());
            path.add(i, new ArrayList<>());
        }

        int loop = N - 1;
        while(loop-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            path.get(a).add(b);
            path.get(b).add(a);
        }

        StringBuilder sb = new StringBuilder();

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            recursion(-1, x, y, doors[x]);
            sb.append(answer).append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static void recursion(int prev, int current, int end, long value) {
        if(current == end){
            answer = value;
            return;
        }

        for(int next: path.get(current)) {
            if(prev == next) continue;
            long pow = (long) (Math.log10(doors[next])) + 1;

            long nval = (value * ((long) Math.pow(10, pow) % MOD)) % MOD;
            recursion(current, next, end, (nval + doors[next]) % MOD);
        }
    }
}

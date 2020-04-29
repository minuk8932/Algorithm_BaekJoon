package network_flow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 12843번: 복수 전공
 *
 * @see https://www.acmicpc.net/problem/12843/
 *
 */
public class Boj12843 {
    private static int[] aMatch, bMatch;
    private static boolean[] visit;

    private static ArrayList<Integer>[] connected;
    private static boolean[] soft;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        soft = new boolean[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int lecture = Integer.parseInt(st.nextToken()) - 1;

            if(st.nextToken().charAt(0) == 's') soft[lecture] = true;
        }

        connected = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            connected[i] = new ArrayList<>();
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int lecture1 = Integer.parseInt(st.nextToken()) - 1;
            int lecture2 = Integer.parseInt(st.nextToken()) - 1;

            if(!soft[lecture1]) connected[lecture1].add(lecture2);
            else connected[lecture2].add(lecture1);
        }

        System.out.println(bipartiteMatch(N));
    }

    private static int bipartiteMatch(int n){
        aMatch = new int[n];
        bMatch = new int[n];
        Arrays.fill(aMatch, -1);
        Arrays.fill(bMatch, -1);

        int count = 0;

        for(int i = 0; i < n; i++) {
            if(aMatch[i] != -1) continue;

            visit = new boolean[n];
            count += recursion(i);
        }

        return n - count;
    }

    private static int recursion(int current) {
        visit[current] = true;

        for(int next: connected[current]) {
            if(bMatch[next] == -1 || (!visit[bMatch[next]] && recursion(bMatch[next]) == 1)) {  // matching, duplicated
                bMatch[next] = current;
                aMatch[current] = next;

                return 1;
            }
        }

        return 0;
    }
}

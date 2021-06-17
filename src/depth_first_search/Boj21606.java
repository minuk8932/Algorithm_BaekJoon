package depth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 21606번: 아침 산책
 *
 * @see https://www.acmicpc.next/21606
 *
 */
public class Boj21606 {

    private static boolean[] visit;
    private static boolean[] inside;
    private static List<Integer>[] path;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        visit = new boolean[N];
        inside = new boolean[N];
        path = new ArrayList[N];

        String place = br.readLine();
        for (int i = 0; i < N; i++) {
            inside[i] = place.charAt(i) == '1';
            path[i] = new ArrayList<>();
        }

        while (N-- > 1) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            path[node1].add(node2);
            path[node2].add(node1);
        }

        System.out.println(search());
    }

    /**
     *
     * Search by in/out
     *
     * line 60 ~ 64: find path by adjacent pair of inside.
     * line 65 ~ 70: find path spanning from outside to inside.
     *
     * @return
     */
    private static long search() {
        long result = 0;

        for(int start = 0; start < inside.length; start++) {
            if(inside[start]) {
                for(int adj: path[start]) {
                    result += inside[adj] ? 1: 0;
                }
            }
            else {
                if(visit[start]) continue;

                long cost = recursion(start, -1);
                result += (cost - 1) * cost;
            }
        }

        return result;
    }

    private static long recursion(int current, int prev) {
        long result = 0;
        visit[current] = true;

        for (int next: path[current]) {
            if (next == prev) continue;

            if (inside[next]) result++;
            else result += recursion(next, current);
        }

        return result;
    }
}

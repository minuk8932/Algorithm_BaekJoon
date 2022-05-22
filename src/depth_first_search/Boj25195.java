package depth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25195번: Yes or yes
 *
 * @see https://www.acmicpc.net/problem/25195
 *
 */
public class Boj25195 {

    private static ArrayList<ArrayList<Integer>> path = new ArrayList<>();
    private static boolean[] waited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        waited = new boolean[N];
        for(int i = 0; i < N; i++) {
            path.add(new ArrayList<>());
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            path.get(u).add(v);
        }

        int S = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while(S-- > 0) {
            int node = Integer.parseInt(st.nextToken()) - 1;
            waited[node] = true;
        }

        System.out.println(recursion(0) ? "yes": "Yes");
    }

    private static boolean recursion(int current) {
        if(waited[current]) return false;
        boolean flag = path.get(current).size() == 0;

        for(int next: path.get(current)) {
            flag |= recursion(next);
        }

        return flag;
    }
}

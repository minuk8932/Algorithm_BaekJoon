package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25187번: 고인물이 싫어요
 *
 * @see https://www.acmicpc.net/problem/25187
 *
 */
public class Boj25187 {

    private static final String STANDING_WATER = "0\n";
    private static final String PURE_WATER = "1\n";

    private static int[] parent;
    private static int[] waterType;
    private static HashMap<Integer, Integer> water = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        init(N);

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            waterType[i] = Integer.parseInt(st.nextToken());
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            merge(x, y);
        }

        waterTypeSetting();

        StringBuilder sb = new StringBuilder();
        while(Q-- > 0) {
            int tank = find(Integer.parseInt(br.readLine()) - 1);
            sb.append(water.get(tank) > 0 ? PURE_WATER: STANDING_WATER);
        }

        System.out.println(sb);
    }

    private static void waterTypeSetting() {

        for(int i = 0; i < waterType.length; i++) {
            int p = find(i);

            if(waterType[i] == 1) water.merge(p, 1, Integer::sum);
            else water.merge(p, -1, Integer::sum);
        }

    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static void merge(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return;

        if(parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }
    }

    private static void init(int n) {
        waterType = new int[n];
        parent = new int[n];
        Arrays.fill(parent, -1);
    }
}

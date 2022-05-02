package disjoint_set;

import java.util.*;
import java.io.*;

/**
 *
 * @author exponential-e
 * 백준 4143번: Bridges and Tunnels
 *
 * @see https://www.acmicpc.net/problem/4143
 *
 */
public class Boj4143 {

    private static final String NEW_LINE = "\n";
    private static Map<String, Integer> buildings;
    private static int[] parent = new int[100_000];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            Arrays.fill(parent, -1);

            buildings = new HashMap<>();
            int index = 0;

            while(n-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String building = st.nextToken();
                String tunnel = st.nextToken();

                if(!buildings.containsKey(building)) {
                    buildings.put(building, index++);
                }

                if(!buildings.containsKey(tunnel)) {
                    buildings.put(tunnel, index++);
                }

                int target = buildings.get(building);
                merge(target, buildings.get(tunnel));
                sb.append(-parent[find(target)]).append(NEW_LINE);
            }
        }

        System.out.println(sb);
    }

    private static int find(int x) {
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static void merge(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return;

        if (parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }
    }
}
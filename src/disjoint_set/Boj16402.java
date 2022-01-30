package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 16402번: 왕국
 *
 * @see https://www.acmicpc.net/problem/16402
 *
 */
public class Boj16402 {

    private static final String DELIMITER = ",";
    private static final Map<String, Integer> INDEX = new HashMap<>();
    private static final String NEW_LINE = "\n";

    private static int[] parent;
    private static String[] countries;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N];
        countries = new String[N];
        for(int i = 0; i < N; i++) {
            String kingdom = br.readLine();

            INDEX.put(kingdom, i);
            countries[i] = kingdom;
            parent[i] = -1;
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine(), DELIMITER);
            int country1 = INDEX.get(st.nextToken());
            int country2 = INDEX.get(st.nextToken());
            int result = Integer.parseInt(st.nextToken());

            if (result == 1) merged(country1, country2);
            else merged(country2, country1);
        }

        System.out.println(printer());
    }

    private static String printer() {
        StringBuilder sb = new StringBuilder();

        List<String> answer = new ArrayList<>();
        for(int i = 0; i < parent.length; i++) {
            if(parent[i] >= 0) continue;
            answer.add(countries[i]);
        }

        Collections.sort(answer);

        sb.append(answer.size()).append(NEW_LINE);
        for(String str: answer) {
            sb.append(str).append(NEW_LINE);
        }

        return sb.toString();
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    /**
     *
     * Merged
     *
     * line 88 ~ 95: find merged country & switch topology
     *
     * @param x
     * @param y
     */
    private static void merged(int x, int y) {
        int px = find(x);
        int py = find(y);

        if(px == py){
            if(parent[x] > parent[y]) {
                parent[x] = parent[y];
                parent[y] = x;
            }

            return;
        }

        parent[px] += parent[py];
        parent[py] = px;
    }
}

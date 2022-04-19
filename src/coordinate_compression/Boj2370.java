package coordinate_compression;

import common.Query;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 *
 * @author exponential-e
 * 백준 2370번: 시장 선거 포스터
 *
 * @see https://www.acmicpc.net/problem/2370
 *
 */
public class Boj2370 {

    private static Map<Integer, Integer> INDEX_MAPPER = new HashMap<>();
    private static int[] poster;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Query<Integer, Integer>> queries = new ArrayList<>();
        Set<Integer> coordinates = new TreeSet<>();

        while(n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            queries.add(new Query.Builder(l).to(r).build());
            coordinates.add(l);
            coordinates.add(r);
        }

        compression(coordinates);

        int accumulate = 0;
        for(Query<Integer, Integer> query: queries) {
            int from = INDEX_MAPPER.get(query.getFrom());
            int to = INDEX_MAPPER.get(query.getTo());

            accumulate++;
            for(int i = from; i <= to; i++) {
                poster[i] = accumulate;
            }
        }

        Set<Integer> count = new HashSet<>();
        for(int p: poster) {
            if(p == 0) continue;
            count.add(p);
        }

        System.out.println(count.size());
    }

    private static void compression(Set<Integer> coordinates) {
        int index = 0;

        for(int coordinate: coordinates) {
            INDEX_MAPPER.put(coordinate, index++);
        }

        poster = new int[index];
    }
}

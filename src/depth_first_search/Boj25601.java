package depth_first_search;

import java.io.*;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 25601번: 자바의 형변환
 *
 * @see https://www.acmicpc.net/problem/25601
 *
 */
public class Boj25601 {

    private static final HashMap<String, Integer> INDEX_MAP = new HashMap<>();
    private static final ArrayList<ArrayList<Integer>> TREE_ORDER = new ArrayList<>();
    private static final ArrayList<ArrayList<Integer>> TREE_DISORDER = new ArrayList<>();

    private static boolean flag = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            TREE_ORDER.add(i, new ArrayList<>());
            TREE_DISORDER.add(i, new ArrayList<>());
        }

        int index = 0;
        while(N-- > 1) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String A = st.nextToken();
            String B = st.nextToken();

            if(!INDEX_MAP.containsKey(A)) INDEX_MAP.put(A, index++);
            if(!INDEX_MAP.containsKey(B)) INDEX_MAP.put(B, index++);

            TREE_ORDER.get(INDEX_MAP.get(B)).add(INDEX_MAP.get(A));
            TREE_DISORDER.get(INDEX_MAP.get(A)).add(INDEX_MAP.get(B));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int from = INDEX_MAP.get(st.nextToken());
        int to = INDEX_MAP.get(st.nextToken());

        casting(TREE_ORDER, from, to);
        casting(TREE_DISORDER, from, to);
        System.out.println(flag ? 1: 0);
    }

    private static void casting(final ArrayList<ArrayList<Integer>> TREE, int src, int snk) {

        for(int next: TREE.get(src)) {
            flag |= next == snk;
            casting(TREE, next, snk);
        }
    }
}
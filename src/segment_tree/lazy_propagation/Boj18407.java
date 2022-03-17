package segment_tree.lazy_propagation;

import common.Pair;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 *
 * @author exponential-e
 * 백준 18407번: 가로 블록 쌓기
 *
 * @see https://www.acmicpc.net/problem/18407
 *
 */
public class Boj18407 {

    private static long[] tree;
    private static long[] lazy;

    private static int start = 1 << 20;
    private static Map<Integer, Integer> indexMapper = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        init();

        List<Pair<Integer>> inputs = new ArrayList<>();
        Set<Integer> values = new TreeSet<>();
        while(N-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            A += B;
            values.add(B);
            values.add(A);

            inputs.add(new Pair.Builder(B, A).build());
        }

        mapping(values);

        long answer = 0;
        for(Pair<Integer> input: inputs) {
            int a = indexMapper.get(input.getFirst());
            int b = indexMapper.get(input.getSecond()) - 1;

            long cost = maxSection(a, b, 1, 1, start);
            answer = Math.max(answer, cost + 1);
            updateSection(a, b, 1, cost + 1, 1, start);
        }

        System.out.println(answer);
    }

    private static void mapping(Set<Integer> values) {
        int index = 1;
        for(int key: values) {
            indexMapper.put(key, index++);
        }
    }

    private static void propagation(int node, int currentStart, int currentEnd) {
        if(lazy[node] == 0) return;

        if(currentStart != currentEnd) {
            int[] son = makeSon(node);
            lazy[son[0]] = Math.max(lazy[node], lazy[son[0]]);
            lazy[son[1]] = Math.max(lazy[node], lazy[son[1]]);
        }

        tree[node] = Math.max(lazy[node], tree[node]);
        lazy[node] = 0;
    }

    private static long maxSection(int start, int end, int node, int currentStart, int currentEnd) {
        propagation(node, currentStart, currentEnd);

        if(start > currentEnd || end < currentStart) return 0;
        if(start <= currentStart && currentEnd <= end) return tree[node];

        int mid = (currentStart + currentEnd) >> 1;
        int[] son = makeSon(node);

        return Math.max(maxSection(start, end, son[0], currentStart, mid),
            maxSection(start, end, son[1], mid + 1, currentEnd));
    }

    private static void updateSection(int start, int end, int node, long value
        , int currentStart, int currentEnd) {
        propagation(node, currentStart, currentEnd);

        if(start > currentEnd || end < currentStart) return;
        if(start <= currentStart && currentEnd <= end) {
            lazy[node] = Math.max(value, lazy[node]);
            propagation(node, currentStart, currentEnd);
            return;
        }

        int mid = (currentStart + currentEnd) >> 1;
        int[] son = makeSon(node);
        updateSection(start, end, son[0], value, currentStart, mid);
        updateSection(start, end, son[1], value, mid + 1, currentEnd);

        tree[node] = Math.max(tree[son[0]], tree[son[1]]);
    }

    private static int[] makeSon(int node) {
        int son = node << 1;
        return new int[] {son, ++son};
    }

    private static void init() {
        int size = start << 1;
        tree = new long[size];
        lazy = new long[size];
    }
}
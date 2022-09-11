package implementation;

import common.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 25565번: 딸기와 토마토
 *
 * @see https://www.acmicpc.net/problem/25565
 *
 */
public class Boj25565 {

    private static final ArrayDeque<Point<Integer, Integer>> ITEMS = new ArrayDeque<>();
    private static final HashMap<Integer, Integer> ROWS = new HashMap<>();
    private static final HashMap<Integer, Integer> COLS = new HashMap<>();
    private static final String NONE = "0";
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";
    private static int[] counts;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                int land = Integer.parseInt(st.nextToken());
                if(land == 0) continue;

                ROWS.merge(i, 1, Integer::sum);
                COLS.merge(j, 1, Integer::sum);
                ITEMS.add(new Point.Builder<Integer, Integer>(i, j).build());
            }
        }

        System.out.print(fieldSpecification(K));
    }

    private static String fieldSpecification(int k) {
        if(ITEMS.size() == (k << 1)) return NONE;

        counts = new int[2];
        ArrayList<Pair<Integer>> pairs = new ArrayList<>();
        pairs.add(findGroups(ROWS, k, 0));
        pairs.add(findGroups(COLS, k, 1));

        if(counts[0] == 2 || counts[1] == 2) return NONE;

        StringBuilder sb = new StringBuilder();

        for(int current = 0; current < 2; current++) {
            Pair<Integer> pair = pairs.get(current);
            if(pair.getFirst() != -1) continue;

            int adjacent = current ^ 1;

            int intercept = (k << 1) - pairs.get(adjacent).getSecond();
            findDuplicatedAlign(pairs.get(adjacent).getSecond() - intercept);

            sb.append(intercept).append(NEW_LINE).append(itemBuilder());
            return sb.toString();
        }

        for(int current = 0; current < 2; current++) {
            int adjacent = current ^ 1;

            if(pairs.get(0).getSecond() != k + current ||
                pairs.get(1).getSecond() != k + (adjacent)) continue;
            return NONE;
        }

        sb.append(1).append(NEW_LINE).append(findDuplicatedList(k));

        return sb.toString();
    }

    private static String itemBuilder() {
        StringBuilder builder = new StringBuilder();

        while(!ITEMS.isEmpty()) {
            Point<Integer, Integer> current = ITEMS.poll();
            builder.append(current.getRow() + 1).append(SPACE).append(current.getCol() + 1)
                .append(NEW_LINE);
        }

        return builder.toString();
    }

    private static void findDuplicatedAlign(int loop) {

        while(loop > 0) {
            ITEMS.pollFirst();
            ITEMS.pollLast();

            loop -= 2;
        }
    }

    private static String findDuplicatedList(int k) {
        while(!ITEMS.isEmpty()) {
            Point<Integer, Integer> current = ITEMS.poll();
            if(ROWS.get(current.getRow()) != k || COLS.get(current.getCol()) != k) continue;

            return (current.getRow() + 1) + SPACE + (current.getCol() + 1) + NEW_LINE;
        }

        return NONE;
    }

    private static Pair<Integer> findGroups(HashMap<Integer, Integer> INDEX, int size, int index) {
        int idx = -1;
        int count = 0;

        for (Map.Entry<Integer, Integer> entry: INDEX.entrySet()) {
            if(entry.getValue() < size) continue;
            if(entry.getValue() == size) counts[index]++;
            count = entry.getValue();
            idx = entry.getKey();
        }

        return new Pair.Builder<>(idx, count).build();
    }
}
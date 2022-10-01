package prefix_sum;

import common.Query;
import java.io.*;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 25606번: 장마
 *
 * @see https://www.acmicpc.net/problem/25606
 *
 */
public class Boj25606 {

    private static final String NEW_LINE ="\n";

    private static int[] boxes;
    private static int[] prefix;
    private static int M;

    private static ArrayList<ArrayList<Integer>> dried = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        prefix = new int[N + 1];
        boxes = new int[N + 1];

        int size = 200_004;
        for(int i = 0; i <= size; i++) {
            dried.add(i, new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            boxes[i] = Integer.parseInt(st.nextToken());
            prefix[i] = boxes[i] + prefix[i - 1];
        }

        Queue<Query<Integer, Integer>> q = new ArrayDeque<>();
        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken()) - 1;
            int day = Integer.parseInt(st.nextToken());

            q.offer(new Query.Builder<Integer, Integer>(cmd).to(day).build());
        }

        System.out.println(dataCheck(q));
    }

    private static String dataCheck(Queue<Query<Integer, Integer>> q) {
        for (int i = 1; i < boxes.length; i++) {
            int index = i + 1;
            dried.get(index).add(i);

            index += boxes[i] / M;
            dried.get(index).add(-i);
        }

        int[][] queries = querySetting();
        StringBuilder sb = new StringBuilder();

        while(!q.isEmpty()) {
            Query<Integer, Integer> current = q.poll();
            sb.append(queries[current.getFrom()][current.getTo()]).append(NEW_LINE);
        }

        return sb.toString();
    }

    private static int[][] querySetting() {
        int[][] dryingAndTotal = new int[2][prefix.length];
        int count = 0;
        int total = 0;

        for (int days = 1; days < prefix.length; days++) {
            int accumulate = 0;

            for(int rain: dried.get(days)) {
                if(rain > 0) {
                    count++;
                    continue;
                }

                count--;
                accumulate += boxes[-rain] % M;
            }

            dryingAndTotal[1][days] = count * M + accumulate;

            total += dryingAndTotal[1][days];
            dryingAndTotal[0][days] = prefix[days] - total;
        }

        return dryingAndTotal;
    }
}
package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiPredicate;

/**
 *
 * @author exponential-e
 * 백준 16965번: 구간과 쿼리
 *
 * @see https://www.acmicpc.net/problem/16965
 *
 */
public class Boj16965 {
    private static final String NEW_LINE = "\n";
    private static ArrayList<Pair> graph = new ArrayList<>();

    private static class Pair {
        int from;
        int to;

        public Pair(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            if (cmd == 1) graph.add(new Pair(f, t));
            else sb.append(bfs(f - 1, t - 1)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int bfs(int start, int end) {
        HashSet<Integer> visit = new HashSet<>();

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        int size = graph.size();
        visit.add(start);

        while(!q.isEmpty()) {
            int current = q.poll();

            for(int index = 0; index < size; index++) {
                Pair next = graph.get(current);

                if (visit.contains(index)) continue;
                if(range.test(next, index)) continue;
                visit.add(index);

                if(index == end) return 1;
                q.offer(index);
            }
        }

        return 0;
    }

    private static final BiPredicate<Pair, Integer> range = (p, x) -> (graph.get(x).from >= p.from || p.from >= graph.get(x).to)
            && (graph.get(x).from >= p.to || p.to >= graph.get(x).to);
}

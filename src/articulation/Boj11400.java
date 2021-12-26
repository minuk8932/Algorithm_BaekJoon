package articulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 11400번: 단절선
 *
 * @see https://www.acmicpc.net/problem/11400
 *
 */
public class Boj11400 {

    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    private static List<Integer>[] path;
    private static List<Line11400> lines = new ArrayList<>();

    private static int count = 1;
    private static int[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        path = new ArrayList[V];
        visit = new int[V];
        for(int i = 0; i < V; i++) {
            path[i] = new ArrayList<>();
        }

        while(E-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            path[node1].add(node2);
            path[node2].add(node1);
        }

        findArticulationLine(0, -1);
        System.out.println(printer());
    }

    /**
     *
     * Articulation checker
     *
     * line 66 ~ 69: 이미 방문한 정점에서 최소 도달 시간 계산
     * line 75: 단절선인 경우 -> 현재 노드보다 자식 노드의 방문 시간이 늦는 경우
     *
     * @param current
     * @param before
     * @return
     */
    private static int findArticulationLine(int current, int before) {
        visit[current] = count++;
        int result = visit[current];

        for (int next: path[current]) {
            if (next == before) continue;
            if (visit[next] != 0) {
                result = Math.min(result, visit[next]);
                continue;
            }

            int time = findArticulationLine(next, current);
            result = Math.min(time, result);

            if (time <= visit[current]) continue;
            lines.add(new Line11400(current, next));
        }

        return result;
    }

    private static String printer() {
        Collections.sort(lines, Comparator
                .comparingInt(Line11400::getFrom)
                .thenComparing(Line11400::getTo)
        );

        StringBuilder sb = new StringBuilder();
        sb.append(lines.size()).append(NEW_LINE);

        for(Line11400 line: lines) {
            sb.append(line.getFrom() + 1).append(SPACE).append(line.getTo() + 1).append(NEW_LINE);
        }

        return sb.toString();
    }
}

class Line11400 {
    private int from;
    private int to;

    public Line11400(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return Math.min(from, to);
    }

    public int getTo() {
        return Math.max(to, from);
    }
}

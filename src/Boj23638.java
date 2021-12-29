import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj23638 {
    private static final String SPACE = " ";

    private static List<Integer>[] path;

    private static int articulations;
    private static int count = 1;
    private static int[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        path = new ArrayList[N];
        visit = new int[N];
        for(int i = 0; i < N; i++) {
            path[i] = new ArrayList<>();
        }

        int loop = N;
        while(loop-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            path[node1].add(node2);
            path[node2].add(node1);
        }

        findArticulationLine(0, -1);
        int nonArt = N - articulations;

        System.out.println(nonArt + " ");
    }

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
            articulations++;
        }

        return result;
    }
}

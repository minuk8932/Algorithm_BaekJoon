import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Boj23835 {

    private static final String NEW_LINE = "\n";

    private static List<Integer>[] rooms;
    private static long[] milk;
    private static long[] passed;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        rooms = new ArrayList[N];
        milk = new long[N];
        for(int i = 0; i < N; i++) {
            rooms[i] = new ArrayList<>();
        }

        int loop = N - 1;
        while(loop-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            rooms[a].add(b);
            rooms[b].add(a);
        }

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());

        passed = new long[N];

        while(Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            if (cmd == 1) {
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;

                Arrays.fill(passed, -1L);
                passed[u] = 0;
                dfs(u, v);

                milk[v] += passed[v];
                passed[v] = -1;
                recursion(v, u);
            }
            else {
                int x = Integer.parseInt(st.nextToken()) - 1;
                sb.append(milk[x]).append(NEW_LINE);
            }
        }

        System.out.print(sb.toString());
    }

    private static void recursion(int current, int target) {
        if(current == target) return;

        for(int next: rooms[current]) {
            if(passed[next] == -1) continue;
            milk[next] += passed[next];
            passed[next] = -1;

            recursion(next, target);
        }
    }

    private static void dfs(int current, int target) {
        if(current == target) return;

        for(int next: rooms[current]) {
            if(passed[next] != -1) continue;
            passed[next] = passed[current] + 1;

            dfs(next, target);
        }
    }
}

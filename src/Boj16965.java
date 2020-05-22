import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj16965 {
    private static final String NEW_LINE = "\n";

    private static ArrayList<Integer>[] graph;
    private static boolean[] visit;

    private static class Pair {
        int idx;
        int from;
        int to;

        public Pair(int idx, int from, int to) {
            this.idx = idx;
            this.from = from;
            this.to = to;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        visit = new boolean[N];

        StringBuilder sb = new StringBuilder();
        ArrayList<Pair> section = new ArrayList<>();

        graph = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        int count = 0;

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            if (cmd == 1) {
                section.add(new Pair(count++, f, t));
            }
            else {
                makeSection(section);
                sb.append(bfs(--f, --t)).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }

    private static int bfs(int start, int end) {
        if (start == end) return 1;

        visit = new boolean[visit.length];

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        visit[start] = true;

        while(!q.isEmpty()) {
            int current = q.poll();

            for(int next: graph[current]) {
                if(visit[next]) continue;
                visit[next] = true;

                if(next == end) return 1;
                q.offer(next);
            }
        }

        return 0;
    }

    private static void makeSection(ArrayList<Pair> sec) {
        for (Pair current: sec) {
            for (Pair another: sec) {
                if (current.to <= another.from || current.from >= another.to) continue;
                graph[current.idx].add(another.idx);
                graph[another.idx].add(current.idx);
            }
        }
    }
}

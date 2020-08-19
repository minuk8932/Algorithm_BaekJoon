import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj16965 {
    private static final String NEW_LINE = "\n";
    private static ArrayList<Integer>[] graph;

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

        StringBuilder sb = new StringBuilder();
        ArrayList<Pair> section = new ArrayList<>();

        graph = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        int count = 1;

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            if (cmd == 1) {
                section.add(new Pair(count++, f, t));
                makeSection(section);
            }
            else {
                sb.append(bfs(f, t)).append(NEW_LINE);
            }
        }

        System.out.println(sb.toString());
    }

    private static int bfs(int start, int end) {
        HashSet<Integer> visit = new HashSet<>();

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        visit.add(start);

        while(!q.isEmpty()) {
            int current = q.poll();

            for(int next: graph[current]) {
                if(visit.contains(next)) continue;
                visit.add(next);

                if(next == end) return 1;
                q.offer(next);
            }
        }

        return 0;
    }

    private static void makeSection(ArrayList<Pair> sec) {
        int size = sec.size();

        for(int i = 0; i < size; i++) {
            for(int j = i + 1; j < size; j++) {
                Pair current = sec.get(i);
                Pair another = sec.get(j);

                if (current.to <= another.from || current.from >= another.to) continue;
                graph[current.idx].add(another.idx);
                graph[another.idx].add(current.idx);
            }
        }
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj24526 {

    private static List<Integer>[] path;

    private static int[] sequence;
    private static int[] scc;
    private static int[] indegree;
    private static int[] level;

    private static int count;
    private static int number;

    private static Deque<Integer> stack;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init(N);

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int snk = Integer.parseInt(st.nextToken());

            path[src].add(snk);
        }

        for(int i = 0; i < N; i++){
            if(sequence[i] != 0) continue;
            recursion(i);
        }

        topologySort();

        int canditate = 0;
        for (int i = 1; i <= number; i++) {
            if (indegree[i] != 0) continue;
            canditate++;
        }

        System.out.print(canditate);
    }

    private static void topologySort() {
        for (int i = 0; i < scc.length; i++) {
            for (int next : path[i]) {
                if (scc[i] == scc[next]) continue;
                indegree[scc[next]]++;
            }
        }
    }

    private static void printer(int root, int candidate) {
        for(int i = 0; i < scc.length; i++) {
            if(scc[i] != root) continue;
            System.out.println(i);
        }
    }
    private static int recursion(int current) {
        stack.push(current);

        sequence[current] = ++count;
        level[current] = count;

        for(int next : path[current]){
            if(sequence[next] == 0) {
                level[current] = Math.min(level[current], recursion(next));
                continue;
            }

            if(scc[next] != 0) continue;
            level[current] = Math.min(level[current], sequence[next]);
        }

        if(level[current] != sequence[current]) return level[current];

        number++;
        while(!stack.isEmpty()){
            int top = stack.pop();

            scc[top] = number;
            if(current == top) break;
        }

        return level[current];
    }

    private static void init(int n) {
        path = new ArrayList[n];
        stack = new ArrayDeque<>();

        sequence = new int[n];
        scc = new int[n];
        level = new int[n];
        indegree = new int[n + 1];

        for(int i = 0; i < n; i++) {
            path[i] = new ArrayList<>();
        }
    }
}

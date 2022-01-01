package strongly_connected_component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 18133번: 가톨릭대학교에 워터 슬라이드를??
 *
 */
public class Boj18133 {

    private static Deque<Integer> stack = new ArrayDeque<>();
    private static List<Integer>[] path;

    private static int[] sequence;
    private static int[] lowest;
    private static int[] scc;
    private static int[] indegree;

    private static int count;
    private static int index;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init(N);

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            path[x].add(y);
        }

        for(int i = 0; i < N; i++) {
            if(sequence[i] != 0) continue;
            recursion(i);
        }

        topologySort();
        System.out.println(printer());
    }

    private static int printer() {
        int roots = 0;

        for (int current = 0; current < path.length; current++) {
            for (int next: path[current]) {
                if (scc[current] != scc[next]) indegree[scc[next]]++;
            }
        }

        for (int i = 1; i <= index; i++) {
            if (indegree[i] != 0) continue;
            roots++;
        }

        return roots;
    }

    /**
     *
     * Indegree setting
     *
     */
    private static void topologySort() {
        for (int i = 0; i < scc.length; i++) {
            for (int next : path[i]) {
                if (scc[i] == scc[next]) continue;
                indegree[scc[next]]++;
            }
        }
    }

    /**
     *
     * Recursion: tarjan
     *
     * @param current
     * @return
     */
    private static int recursion(int current) {
        sequence[current] = ++count;
        lowest[current] = count;

        stack.push(current);

        for(int next : path[current]){
            if(sequence[next] == 0) {
                lowest[current] = Math.min(lowest[current], recursion(next));
                continue;
            }

            if(scc[next] != 0) continue;
            lowest[current] = Math.min(lowest[current], sequence[next]);
        }

        if(lowest[current] != sequence[current]) return lowest[current];

        index++;
        while(!stack.isEmpty()){
            int top = stack.pop();

            scc[top] = index;
            if(current == top) break;
        }

        return lowest[current];
    }

    private static void init(int n) {
        sequence = new int[n];
        lowest = new int[n];
        indegree = new int[n + 1];
        scc = new int[n];

        path = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            path[i] = new ArrayList<>();
        }
    }
}

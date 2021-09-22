package topology_sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 23059번: 리그 오브 레게노
 *
 * @see https://www.acmicpc.net/problem/23059
 *
 */
public class Boj23059 {

    private static final Map<String, Integer> ITEMS = new HashMap<>();
    private static final Map<Integer, String> REV = new HashMap<>();

    private static final String NEW_LINE = "\n";

    private static List<Node> query = new ArrayList<>();
    private static List<Integer>[] path;
    private static int[] indegree;

    private static class Node {
        int from;
        int to;

        public Node(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    private static class Item {
        String name;
        int index;

        public Item(String name, int index) {
            this.name = name;
            this.index = index;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = PARSE.apply(br.readLine());

        indegree = new int[N];
        int size = 0;

        while(N-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String from = st.nextToken();
            String to = st.nextToken();

            if(!ITEMS.containsKey(from)) {
                REV.put(size, from);
                ITEMS.put(from, size++);
            }

            if(!ITEMS.containsKey(to)) {
                REV.put(size, to);
                ITEMS.put(to, size++);
            }

            query.add(new Node(ITEMS.get(from), ITEMS.get(to)));
        }

        init(size);
        System.out.println(topologySort());
    }

    /**
     *
     * Topology sort
     *
     * line 103: find equivalent level nodes and searching
     *
     * @return
     */
    private static String topologySort() {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Item> pq = new PriorityQueue<>(Comparator.comparing(i -> i.name));
        List<Item> start = new ArrayList<>();

        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] != 0) continue;
            pq.offer(new Item(REV.get(i), i));
            start.add(new Item(REV.get(i), i));
        }

        Collections.sort(start, Comparator.comparing(i -> i.name));
        for(Item s: start) {
            indegree[s.index]--;
            sb.append(s.name).append(NEW_LINE);
        }

        while(!pq.isEmpty()) {
            int size = pq.size();
            List<Item> candidate = new LinkedList<>();

            while(size-- > 0) {
                Item current = pq.poll();
                if(indegree[current.index] == 0) sb.append(current.name).append(NEW_LINE);

                for (int next : path[current.index]) {
                    indegree[next]--;
                    if (indegree[next] != 0) continue;

                    candidate.add(new Item(REV.get(next), next));
                }
            }

            while(!candidate.isEmpty()) {
                pq.offer(candidate.remove(0));
            }
        }

        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] > 0) return "-1";
        }

        return sb.toString();
    }

    private static void init(int N) {
        path = new ArrayList[N];
        indegree = new int[N];
        for(int i = 0; i < N; i++) {
            path[i] = new ArrayList<>();
        }

        for(Node q: query) {
            path[q.from].add(q.to);
            indegree[q.to]++;
        }
    }

    private static final Function<String, Integer> PARSE = Integer::parseInt;

}

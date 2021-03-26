package tree_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 21276번: 계보 복원가 호석
 *
 * @see https://www.acmicpc.net/problem/21276
 *
 */
public class Boj21276 {

    private static ArrayList<Integer>[] graph;
    private static HashMap<String, Integer> index = new HashMap<>();
    private static HashMap<Integer, String> name = new HashMap<>();

    private static int[] indegree;

    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        indegree = new int[N];
        graph = new ArrayList[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            String person = st.nextToken();

            index.put(person, i);
            name.put(i, person);
            graph[i] = new ArrayList<>();
        }

        int M = Integer.parseInt(br.readLine());
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            String X = st.nextToken();
            String Y = st.nextToken();

            int x = index.get(X);
            int y = index.get(Y);

            indegree[x]++;
            graph[y].add(x);
        }

        System.out.println(recovery());
    }

    /**
     *
     * Recovery
     *
     * Just implementation..
     *
     * @return
     */
    private static String recovery() {
        StringBuilder sb = new StringBuilder();

        TreeMap<String, Integer> rank = new TreeMap<>();
        ArrayList<String> ancestor = new ArrayList<>();

        int size = 0;
        for(int i = 0; i < indegree.length; i++) {
            rank.put(name.get(i), indegree[i]);

            if(indegree[i] != 0) continue;
            ancestor.add(name.get(i));
            size++;
        }

        sb.append(size).append(NEW_LINE);
        Collections.sort(ancestor);

        for(String an: ancestor) {
            sb.append(an).append(SPACE);
        }

        sb.append(NEW_LINE);

        for(Map.Entry<String, Integer> person: rank.entrySet()) {
            sb.append(person.getKey()).append(SPACE);

            int count = 0;
            int level = person.getValue();
            int current = index.get(person.getKey());

            ArrayList<String> list = new ArrayList<>();

            for (int next: graph[current]) {
                if(rank.get(name.get(next)) != level + 1) continue;
                count++;
                list.add(name.get(next));
            }

            Collections.sort(list);
            sb.append(count).append(SPACE);

            for(String str: list) {
                sb.append(str).append(SPACE);
            }

            sb.append(NEW_LINE);
        }

        return sb.toString();
    }
}

package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 4803번: 트리
 *
 * @see https://www.acmcipc.net/problem/4803
 *
 */
public class Boj4803 {

    private static int[] parent;
    private static boolean[] except;

    private static final String CASE = "Case ";
    private static final String COLONE = ": ";

    private static final String NONE = "No trees.\n";

    private static final String A = "There is one tree.\n";

    private static final String MANY = "A forest of ";
    private static final String TREE = " trees.\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = 1;

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0) break;

            parent = new int[N];
            except = new boolean[N];
            Arrays.fill(parent, -1);

            List<Integer> candidate = new LinkedList<>();

            while(M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken()) - 1;
                int node2 = Integer.parseInt(st.nextToken()) - 1;

                boolean flag = merged(node1, node2);

                if(!flag) continue;
                candidate.add(node1);
                candidate.add(node2);
            }

            exceptTreeChecker(candidate);

            int count = counting();

            sb.append(CASE).append(T++).append(COLONE);
            if (count == 0) sb.append(NONE);
            else if (count == 1) sb.append(A);
            else sb.append(MANY).append(count).append(TREE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Except tree checker
     *
     * line 82: except tree when all trees are merged.
     *
     * @param candi
     */
    private static void exceptTreeChecker(List<Integer> candi) {
        while(!candi.isEmpty()) {
            except[find(candi.remove(0))] = true;
        }
    }

    private static int counting() {
        int count = 0;

        for(int i = 0; i < parent.length; i++) {
            if(parent[i] >= 0 || except[i]) continue;
            count++;
        }

        return count;
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merged(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return true;

        if(parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }

        return false;
    }
}

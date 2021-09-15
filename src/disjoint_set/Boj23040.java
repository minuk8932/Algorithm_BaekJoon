package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 23040번: 누텔라 트리 (Easy)
 *
 * @see https://www.acmicpc.net/problem/23040
 *
 */
public class Boj23040 {

    private static List<Integer> startNodes = new ArrayList<>();
    private static List<Integer>[] tree;

    private static int[] parent;
    private static boolean[] red;

    private static final char RED = 'R';

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = PARSE.apply(br.readLine());

        tree = new ArrayList[N];
        red = new boolean[N];
        parent = new int[N];
        for(int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
            parent[i] = -1;
        }

        int loop = N;
        while(loop-- > 1) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int vi = PARSE.apply(st.nextToken()) - 1;
            int vj = PARSE.apply(st.nextToken()) - 1;

            tree[vi].add(vj);
            tree[vj].add(vi);
        }

        char[] colors = br.readLine().toCharArray();
        for(int i = 0; i < colors.length; i++){
            red[i] = colors[i] == RED;
            if(!red[i]) continue;

            startNodes.add(i);
        }

        disJointSet();
        System.out.println(nutella());
    }

    /**
     *
     * Nutella
     *
     * line 73 ~ 76: count black nodes, adjacent by red set
     *
     * @return
     */
    private static long nutella() {
        long[] count = new long[parent.length];
        long answer = 0;

        for(int i = 0; i < parent.length; i++) {
            int p = find(i);

            for(int adj: tree[i]) {
                if(red[adj]) continue;
                count[p]++;
            }
        }

        for(int i = 0; i < count.length; i++) {
            if(!red[i] || parent[i] >= 0) continue;
            answer += count[i] * -parent[i];
        }

        return answer;
    }

    private static void disJointSet() {
        for(int start: startNodes) {
            Queue<Integer> q = new ArrayDeque<>();
            q.offer(start);

            while(!q.isEmpty()) {
                int current = q.poll();

                for(int next: tree[current]) {
                    if(!red[next]) continue;
                    if(merge(next, current)) continue;

                    q.offer(next);
                }
            }
        }
    }

    private static int find(int x) {
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merge(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return true;

        if (parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }

        return false;
    }

    private static final Function<String, Integer> PARSE = Integer::parseInt;
}

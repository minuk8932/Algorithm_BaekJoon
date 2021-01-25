package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 1039번: 교환
 *
 * @see https://www.acmicpc.net/problem/1039
 *
 */
public class Boj1039 {

    private static boolean[][] visit;

    private static class Switch {
        int value;
        int count;

        public Switch(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        visit = new boolean[K + 1][1_000_001];

        System.out.println(bfs(N, K));
    }

    /**
     *
     * BFS
     *
     * line 57: switching limit
     * line 65: get max value
     *
     * @param n
     * @param k
     * @return
     */
    private static int bfs(int n, int k) {
        Queue<Switch> q = new LinkedList<>();
        q.offer(new Switch(n, 0));

        visit[0][n] = true;
        int max = -1;

        while(!q.isEmpty()) {
            Switch current = q.poll();

            ArrayList<Integer> nextList = makeNext(current.value);
            for(int next: nextList) {
                if(current.count + 1 > k) continue;
                if(visit[current.count + 1][next]) continue;
                visit[current.count + 1][next] = true;

                if(current.count + 1 == k) max = Math.max(next, max);
                q.offer(new Switch(next, current.count + 1));
            }
        }

        return max;
    }

    /**
     *
     * line 95 ~ 110: find all cases
     *
     * @param a
     * @return
     */
    private static ArrayList<Integer> makeNext(int a) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        while(a > 0) {
            stack.push(a % 10);
            a /= 10;
        }

        ArrayList<Integer> target = new ArrayList<>();
        while(!stack.isEmpty()) {
            target.add(stack.pop());
        }

        int size = target.size();

        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            for(int j = i + 1; j < size; j++) {
                if(i == 0 && target.get(j) == 0) continue;

                int value = 0;
                for(int k = 0; k < size; k++) {
                    value *= 10;

                    if(k == i) value += target.get(j);
                    else if(k == j) value += target.get(i);
                    else value += target.get(k);
                }

                result.add(value);
            }
        }

        return result;
    }
}

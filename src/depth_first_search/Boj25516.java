package depth_first_search;

import java.io.*;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 25516번: 거리가 k이하인 트리 노드에서 사과 수확하기
 *
 * @see https://www.acmicpc.net/problem/25516
 *
 */
public class Boj25516 {

    private static final ArrayList<ArrayList<Integer>> TREE = new ArrayList<>();
    private static int[] apples;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        apples = new int[n];
        for(int i = 0; i < n; i++) {
            TREE.add(i, new ArrayList<>());
        }

        int loop = n - 1;
        while(loop-- > 0) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            TREE.get(p).add(c);
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            apples[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(recursion(0, k) + apples[0]);
    }

    private static int recursion(int current, int depth) {
        int answer = 0;

        for(int next: TREE.get(current)) {
            answer += recursion(next, depth - 1) + (depth > 0 ? apples[next] : 0);
        }

        return answer;
    }
}

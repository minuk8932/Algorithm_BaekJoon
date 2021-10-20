package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 23253번: 자료구조는 정말 최고야
 *
 * @see https://www.acmicpc.net/problem/23253
 *
 */
public class Boj23253 {

    private static List<Deque<Integer>> room;
    private static int[] index;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        room = new ArrayList<>();
        index = new int[N];

        for(int i = 0; i < M; i++) {
            room.add(new ArrayDeque<>());

            int k = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            while(k-- > 0) {
                int book = Integer.parseInt(st.nextToken()) - 1;
                room.get(i).push(book);
                index[book] = i;
            }
        }

        System.out.println(topologyOrderCheck(N));
    }

    private static String topologyOrderCheck(int n) {
        boolean flag = false;

        for (int book = 0; book < n; book++) {
            Deque<Integer> stack = room.get(index[book]);
            if(flag = (stack.isEmpty() || stack.peek() != book)) break;

            stack.pop();
            continue;
        }

        return !flag ? "Yes": "No";
    }
}

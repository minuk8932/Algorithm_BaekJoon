package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20055번: 컨베이어 벨트 위의 로봇
 *
 * @see https://www.acmicpc.net/problem/20055
 *
 */
public class Boj20055 {
    private static int zero;
    private static int cursor;
    private static int[] belt;
    private static boolean[] ocuppied;

    private static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        belt = new int[N * 2];
        ocuppied = new boolean[belt.length];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < belt.length; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(rotating(N, K));
    }

    private static int rotating(int n, int k) {
        int count = 0;

        while(zero < k) {
            int removed = level1(n);
            level2(removed);
            level3();

            count++;
        }

        return count;
    }

    private static int level1(int n) {              // level1 belt move
        cursor--;
        if(cursor < 0) cursor = belt.length - 1;

        int removed = (cursor + n) % belt.length;
        if(q.isEmpty()) return removed;

        if(removed == q.peek()) {                   // dropped
            q.poll();
            ocuppied[removed] = false;
        }

        return removed;
    }

    private static void level2(int removed) {       // level2 robot move
        Queue<Integer> temp = new LinkedList<>();

        while(!q.isEmpty()) {
            int current = q.poll();

            ocuppied[current] = false;

            int next = (current + 1) % belt.length;
            if(next == removed) continue;            // dropped

            if(!ocuppied[next] && belt[next] > 0){   // move
                belt[next]--;
                if(belt[next] == 0) zero++;
            }
            else{
                next = current;                      // stay
            }

            ocuppied[next] = true;
            temp.offer(next);
        }

        while(!temp.isEmpty()) {
            q.offer(temp.poll());
        }
    }

    private static void level3(){
        if(!ocuppied[cursor] && belt[cursor] > 0) {  // put
            ocuppied[cursor] = true;

            belt[cursor]--;
            if(belt[cursor] == 0) zero++;

            q.offer(cursor);
        }
    }
}

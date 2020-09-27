package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author exponential-e
 * 백준 15828번: Router
 *
 * @see https://www.acmicpc.net/problem/15828
 *
 */
public class Boj15828 {
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> q = new LinkedList<>();

        int size = 0;
        int n = Integer.parseInt(br.readLine());

        while(true) {
            int data = Integer.parseInt(br.readLine());
            if (data == -1) break;

            if(data == 0) {
                q.poll();
                size--;

                continue;
            }

            if(size == n) continue;

            q.offer(data);
            size++;
        }

        System.out.println(printer(q));
    }
    private static String printer(Queue<Integer> q) {
        if(q.isEmpty()) return "empty";

        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()) {
            sb.append(q.poll()).append(SPACE);
        }

        return sb.toString();
    }
}

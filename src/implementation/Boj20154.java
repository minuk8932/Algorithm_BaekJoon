package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author exponential-e
 * 백준 20154번: 이 구역 승자는 누구야?!
 *
 * @see https://www.acmicpc.net/problem/20154
 *
 */
public class Boj20154 {
    private static Queue<Integer> q = new LinkedList<>();
    private static HashMap<Character, Integer> mapper = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        converter();

        for(char c: br.readLine().toCharArray()) {
            q.offer(mapper.get(c));
        }

        System.out.println(tournament());
    }

    private static String tournament() {
        int size = q.size();

        while(size > 1) {
            if(size % 2 == 1) {
                q.offer(0);
                size++;
            }

            while(size > 1) {
                int a = q.poll();
                int b = q.poll();

                q.offer((a + b) % 10);
                size -= 2;
            }

            size = q.size();
        }

        StringBuilder sb = new StringBuilder();
        int result = q.poll();

        return sb.append(result % 2 == 1 ? "I'm a winner!": "You're the winner?").toString();
    }

    private static void converter() {
        char start = 'A', end = 'D';

        saver(start ,end, -1, 3);

        start = end; end = 'F';
        saver(start, end, 1, 2);

        start = end; end = 'I';
        saver(start, end, 0, 3);

        start = end; end = 'K';
        saver(start, end, 0, 1);

        mapper.put(end, 3); mapper.put('L', 1);

        start = 'M'; end = 'O';
        saver(start, end, 0, 3);

        mapper.put(end, 1);

        start = 'P'; end = 'S';
        saver(start, end, 0, 2);

        mapper.put(end, 1); mapper.put('T', 2);

        start = 'U'; end = 'W';
        saver(start, end, 0, 1);

        start = end; end = 'Z';
        saver(start, end, 0, 2);

        mapper.put(end, 1);
    }

    private static void saver(char s, char e, int adder, int value) {
        for(char c = s; c < e; c++) {
            mapper.put(c, value);
            value += adder;
        }
    }
}

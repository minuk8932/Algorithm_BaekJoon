package greedy;

import common.Time;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2594번: 놀이 공원
 *
 * @see https://www.acmicpc.net/problem/2594
 *
 */
public class Boj2594 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Time<Integer, Integer>> pq = new PriorityQueue<>(
            Comparator
                .comparingInt(Time<Integer, Integer>::getStart));

        while(N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            pq.offer(new Time
                .Builder(timeToMinutes(start, -10), 1)
                .build());

            pq.offer(new Time
                .Builder(timeToMinutes(end, 10), -1)
                .build());
        }

        System.out.println(withYou(pq));
    }

    private static int withYou(PriorityQueue<Time<Integer, Integer>> pq) {
        int with = 0;
        int accumulate = 0;

        boolean flag = true;

        int time = 600;
        while(!pq.isEmpty()) {
            Time<Integer, Integer> current = pq.poll();
            accumulate += current.getEnd();

            if(accumulate == 1 && flag){
                with = Math.max(with, current.getStart() - time);
                flag = false;
            }

            if(accumulate == 0){
                time = current.getStart();
                flag = true;
            }
        }

        with = Math.max(with, 1320 - time);
        return with;
    }

    private static int timeToMinutes(int time, int interval) {
        return (time / 100 * 60 + time % 100) + interval;
    }
}
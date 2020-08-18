package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2065번: 나룻배
 *
 * @see https://www.acmicpc.net/problem/2065
 *
 */
public class Boj2065 {
    private static Queue<Passenger> leftQ = new LinkedList<>();
    private static Queue<Passenger> rightQ = new LinkedList<>();

    private static int[] arrived = new int[10_001];
    private static final String NEW_LINE = "\n";

    private static class Passenger {
        int index;
        int time;
        boolean isLeft;

        public Passenger(int index, int time, boolean isLeft) {
            this.index = index;
            this.time = time;
            this.isLeft = isLeft;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            Passenger p = new Passenger(i, Integer.parseInt(st.nextToken()), "left".equals(st.nextToken()));
            if(p.isLeft) leftQ.offer(p);            // get on the boat left side
            else rightQ.offer(p);
        }

        System.out.println(patrol(M, t, N));
    }

    private static String patrol(int m, int t, int n) {
        boolean boatIsLeft = true;
        int spend = 0;

        while(!leftQ.isEmpty() || !rightQ.isEmpty()) {
            boolean flag = false;

            if(!leftQ.isEmpty()) {                                      // left
                Passenger current = leftQ.peek();

                if(current.time <= spend) {
                    int count = 0;
                    flag = true;

                    if(!boatIsLeft) spend += t;                         // back to side any passenger
                    else boatIsLeft = false;

                    while(!leftQ.isEmpty()) {
                        current = leftQ.peek();
                        if(current.time > spend || count >= m) break;   // patrolling
                        arrived[current.index] = spend + t;             // save time

                        count++;
                        leftQ.poll();
                    }

                    spend += t;
                }
            }

            if(!rightQ.isEmpty()) {                                     // right
                Passenger current = rightQ.peek();

                if(current.time <= spend) {
                    int count = 0;
                    flag = true;

                    if(boatIsLeft) spend += t;
                    else boatIsLeft = true;

                    while(!rightQ.isEmpty()) {
                        current = rightQ.peek();
                        if(current.time > spend || count >= m) break;
                        arrived[current.index] = spend + t;

                        count++;
                        rightQ.poll();
                    }

                    spend += t;
                }
            }

            if(!flag) spend++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(arrived[i]).append(NEW_LINE);
        }

        return sb.toString();
    }
}

package heap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 23246번: Sport Climbing Combined
 *
 * @see https://www.acmicpc.net/problem/23246
 *
 */
public class Boj23246 {

    private static Queue<Player> pq;

    private static final String SPACE = " ";

    private static class Player {
        private int backNumber;
        private int lead;
        private int speed;
        private int bouldering;

        public Player(int backNumber, int lead, int speed, int bouldering) {
            this.backNumber = backNumber;
            this.lead = lead;
            this.speed = speed;
            this.bouldering = bouldering;
        }

        public int multiply() {
            return this.lead * this.bouldering * this.speed;
        }

        public int sum() {
            return this.lead + this.bouldering + this.speed;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>(Comparator.comparingInt(Player::multiply)
            .thenComparingInt(Player::sum)
            .thenComparingInt(Player -> Player.backNumber));

        while(N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int back = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            pq.offer(new Player(back, l, s, b));
        }

        System.out.println(thalamus(3));
    }

    private static String thalamus(int limit) {
        StringBuilder sb = new StringBuilder();

        while(limit-- > 0) {
            sb.append(pq.poll().backNumber).append(SPACE);
        }

        return sb.toString();
    }
}

package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2886번: 자리 전쟁
 *
 * @see https://www.acmicpc.net/problem/2886/
 *
 */
public class Boj2886 {
    private static final char PERSON = 'X';
    private static final char SEAT = 'L';

    private static ArrayList<Integer> people = new ArrayList<>();
    private static ArrayList<Integer> seats = new ArrayList<>();

    private static int[] value;
    private static int R, C;

    private static class Seat implements Comparable<Seat>{
        int seat;
        int dist;
        int pers;

        public Seat(int seat, int dist, int pers){
            this.seat = seat;
            this.dist = dist;
            this.pers = pers;
        }

        @Override
        public int compareTo(Seat s) {
            return this.dist < s.dist ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        for(int i = 0; i < R; i++){
            String line = br.readLine();

            for(int j = 0; j < C; j++){
                char pick = line.charAt(j);

                if(pick == PERSON) people.add(i * C + j);
                if(pick == SEAT) seats.add(i * C + j);
            }
        }

        System.out.println(battle());
    }

    private static int battle(){
        PriorityQueue<Seat> pq = new PriorityQueue<>();
        int count = 0;

        for(int p: people) {
            for (int s : seats) {
                int status = distance(s, p);
                pq.offer(new Seat(s, status, p));           // gathering data
            }
        }

        value = new int[20_001];
        boolean[] already = new boolean[10_000];
        int[] owner = new int[20_001];

        while(!pq.isEmpty()){
            Seat current = pq.poll();
            if(already[current.pers]) continue;

            if(value[current.seat] == 0){                   // find owner
                already[current.pers] = true;
                value[current.seat] = 1;
                owner[current.seat] = current.dist;
                continue;
            }

            if(value[current.seat] == 1 && owner[current.seat] == current.dist){        // find competitors
                already[current.pers] = true;
                count++;
                value[current.seat] = 2;
            }
        }

        return count;
    }

    private static int distance(int seat, int person){
        int rowDiff = (seat / C - person / C);
        int colDiff = (seat % C - person % C);

        return rowDiff * rowDiff + colDiff * colDiff;
    }
}

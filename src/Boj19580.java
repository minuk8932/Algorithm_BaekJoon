import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj19580 {
    private static PriorityQueue<Range> citizen = new PriorityQueue<>();
    private static Mask[] store;

    private static class Range implements Comparable<Range>{
        long from;
        long to;

        public Range(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Range r) {
            if (this.to < r.to) return 1;
            else if(this.to > r.to) return -1;
            else {
                if(this.from < r.from) return 1;
                else if(this.from > r.from) return -1;
                else return 0;
            }
        }
    }

    private static class Mask implements Comparable<Mask>{
        long cost;
        int quantity;

        public Mask(long cost, int quantity) {
            this.cost = cost;
            this.quantity = quantity;
        }

        @Override
        public int compareTo(Mask r) {
            return this.cost < r.cost ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            citizen.offer(new Range(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())));
        }

        store = new Mask[M];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            store[i] = new Mask(-Long.parseLong(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(store);
        System.out.println(buying());
    }

    private static int buying() {
        PriorityQueue<Range> pq = new PriorityQueue<>();            // candidate
        int count = 0;

        for(Mask current: store) {
            while(!citizen.isEmpty() && -current.cost < citizen.peek().from){
                citizen.poll();
            }

            while(!citizen.isEmpty() && citizen.peek().from <= -current.cost && -current.cost <= citizen.peek().to){
//                System.out.println(-current.cost + " " + citizen.peek().from + " " + citizen.peek().to);
                pq.offer(citizen.poll());
            }

//            while(!pq.isEmpty() && current.cost > pq.peek().to){
//                System.out.println(current.cost + " " + pq.peek().from + " " + pq.peek().to);
//                pq.poll();
//            }

            int loop = current.quantity;
            while(loop-- > 0) {
                if(pq.isEmpty()) break;
                pq.poll();
                count++;
            }
        }

        return count;
    }
}

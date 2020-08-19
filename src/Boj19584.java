import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj19584 {
    private static PriorityQueue<RailRoad> rail = new PriorityQueue<>();
    private static PriorityQueue<RailRoad> except;

    private static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class RailRoad implements Comparable<RailRoad>{
        Coordinate c1;
        Coordinate c2;
        long cost;

        public RailRoad(Coordinate c1, Coordinate c2, long cost) {
            this.c1 = c1;
            this.c2 = c2;
            this.cost = cost;
        }

        @Override
        public int compareTo(RailRoad that) {
            int thisMin = Math.min(this.c1.y, this.c2.y);
            int thatMin = Math.min(that.c1.y, that.c2.y);

            return thisMin < thatMin ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Coordinate[] primary = new Coordinate[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            primary[i] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            rail.offer(new RailRoad(primary[Integer.parseInt(st.nextToken()) - 1], primary[Integer.parseInt(st.nextToken()) -1],
                    Long.parseLong(st.nextToken())));
        }

        except = new PriorityQueue<>(new Comparator<RailRoad>() {
            @Override
            public int compare(RailRoad r1, RailRoad r2) {
                int thisMax = Math.max(r1.c1.y, r1.c2.y);
                int thatMax = Math.max(r2.c1.y, r2.c2.y);

                return thisMax < thatMax ? -1: 1;
            }
        });

        System.out.println(develop());
    }

    private static long develop() {
        long cost = 0;
        long max = 0;

        while(!rail.isEmpty()) {
            RailRoad current = rail.poll();
            cost += current.cost;

            except.offer(current);
            RailRoad ex = except.peek();
            int deadline = Math.max(ex.c1.y, ex.c2.y);
            int currentLine = Math.min(current.c1.y, current.c2.y);

            if(deadline < currentLine){
                cost -= except.poll().cost;
            }

            max = Math.max(cost, max);
        }

        return max;
    }
}

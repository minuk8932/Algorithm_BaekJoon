import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj18298 {
    private static class Coordinate {
        long x;
        long y;

        public Coordinate(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long sqr = 0;

        while(N-- > 0) {                                    // shoelace
            int P = Integer.parseInt(br.readLine());

            Coordinate[] coor = new Coordinate[P];
            for(int i = 0; i < P; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                coor[i] = new Coordinate(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
            }

            long size = 0;

            Coordinate s = coor[0];
            for(int i = 1; i < P; i++) {
                size += ccw(s, coor[i], coor[(i + 1) % P]);
            }

            if(size < 0) size = -size;
            sqr += size / 2;
        }

        System.out.println(sqr);
    }

    private static long ccw(Coordinate c, Coordinate a, Coordinate b){
        long f = (a.x - c.x) * (b.y - c.y);
        long s = (a.y - c.y) * (b.x - c.x);
        return f - s;
    }
}

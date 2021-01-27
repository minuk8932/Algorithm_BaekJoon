import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj20607 {

    private static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Coordinate[] A = new Coordinate[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Coordinate[] B = new Coordinate[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            B[i] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Coordinate[] sign = new Coordinate[K];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            sign[i] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int result = inAndOut(A, B, sign);
        System.out.println(result == 0 ? "YES": result);
    }

    private static int inAndOut(Coordinate[] out, Coordinate[] in, Coordinate[] sign) {
        int count = 0;

        for(Coordinate s: sign) {
            if(isInside(s, in)) count++;
            if(!isInside(s, out)) count++;
        }

        return count;
    }

    /**
     * Dot position analysis
     *
     * line 64 dotted between target[src] and target[snk]
     * line 66 crossed point of dot's x line & line(target[src], target[snk])
     *
     * @param dot
     * @param target
     * @return
     */
    private static boolean isInside(Coordinate dot, Coordinate[] target){
        int crossed = 0;

        for(int src = 0 ; src < target.length; src++){
            int snk = (src + 1) % target.length;

            if((target[src].y > dot.y) != (target[snk].y > dot.y) ){
                if(dot.x < cross(dot, target[src], target[snk])) crossed++;
            }
        }
        return crossed % 2 > 0;
    }

    private static double cross(Coordinate d, Coordinate src, Coordinate snk) {
        return (snk.x - src.x) * (d.y - src.y) / (double) (snk.y - src.y) + (double) src.x;
    }
}

import common.Coordinate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2492 {

    private static int N, M, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Coordinate[] diamonds = new Coordinate[T];
        for(int i = 0; i < diamonds.length; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            diamonds[i] = new Coordinate.Builder(X, Y).build();
        }

        System.out.println(coverage(diamonds));
    }

    private static String coverage(Coordinate[] diamonds) {


        return "";
    }
}

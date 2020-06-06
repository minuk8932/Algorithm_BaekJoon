package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17371번: 이사
 *
 * @see https://www.acmicpc.net/problem/17371/
 *
 */
public class Boj17371 {
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
        int N = Integer.parseInt(br.readLine());

        Coordinate[] coors = new Coordinate[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            coors[i] = new Coordinate(x, y);
        }

        Coordinate result = findDiameter(N, coors);
        System.out.println(result.x + " " + result.y);
    }

    private static Coordinate findDiameter(int n, Coordinate[] arr){
        Coordinate value = new Coordinate(0, 0);
        int min = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++) {                                    // hypothesis: find the shortest ? one of convenience
            int d = 0;

            for(int j = 0; j < n; j++) {
                if(i == j) continue;
                d = Math.max(d, euclidianDistance(arr[i], arr[j]));     // find max distance with another
            }

            if(d < min) {
                min = d;
                value = arr[i];
            }
        }

        return value;
    }

    private static int euclidianDistance(Coordinate c1, Coordinate c2) {
        return (c1.x - c2.x) * (c1.x - c2.x) + (c1.y - c2.y) * (c1.y - c2.y);
    }
}

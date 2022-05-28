package prefix_sum;

import common.Coordinate;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25174번: 힘겨운 쿠기의 식당 개업기
 *
 * @see https://www.acmicpc.net/problem/25174
 *
 */
public class Boj25174 {

    private static Coordinate<Integer, Integer>[] coordinates;
    private static Coordinate<Integer, Integer>[] cache;
    private static Coordinate<Integer, Integer> end;
    private static int total;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        coordinates = new Coordinate[N];
        cache = new Coordinate[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            coordinates[i] = new Coordinate.Builder(x, y).r(z).build();
            cache[i] = new Coordinate.Builder(0, 0).build();
            total += z;
        }

        System.out.println(open());
    }

    private static int open() {
        Arrays.sort(coordinates, Comparator.comparingInt(Coordinate::getX));
        end = compressionXBuilder();

        Arrays.sort(coordinates, Comparator.comparingInt(Coordinate::getY));
        end = compressionYBuilder(end.getX());

        int[][] values = new int[end.getX() + 1][end.getY() + 1];
        int[][] sum = new int[end.getX() + 1][end.getY() + 1];

        for(Coordinate<Integer, Integer> coor: coordinates) {
            values[coor.getX()][coor.getY()] = coor.getR();
        }

        sum[0][0] = values[0][0];
        for(int x = 1; x <= end.getX(); x++) {
            sum[x][0] = sum[x - 1][0] + values[x][0];
        }

        for(int y = 1; y <= end.getY(); y++) {
            sum[0][y] = sum[0][y - 1] + values[0][y];
        }

        for(int x = 1; x <= end.getX(); x++) {
            for(int y = 1; y <= end.getY(); y++) {
                sum[x][y] = sum[x][y - 1] + sum[x - 1][y] - sum[x - 1][y - 1] + values[x][y];
            }
        }

        int answer = total;
        for(int x = 0; x <= end.getX(); x++) {
            for(int y = 0; y <= end.getY(); y++) {
                int[] quadrant = getQuadrantValues(x, y, sum);

                int max = Arrays.stream(quadrant).max().getAsInt();
                int min = Arrays.stream(quadrant).min().getAsInt();
                answer = Math.min(answer, max - min);
            }
        }

        return answer;
    }

    private static int[] getQuadrantValues(int x, int y, int[][] sum) {
        int[] q = {
            sum[x][end.getY()],
            sum[x][y],
            sum[end.getX()][y],
            sum[end.getX()][end.getY()]
        };

        q[0] -= q[1];
        q[2] -= q[1];
        q[3] -= (q[1] + q[2] + q[0]);
        return q;
    }

    private static Coordinate<Integer, Integer> compressionYBuilder(int x) {
        Coordinate<Integer, Integer> coor = new Coordinate.Builder(x, 0).build();

        for(int i = 1; i < coordinates.length; i++) {
            if (coordinates[i].getY() == coordinates[i - 1].getY()) {
                cache[i] = new Coordinate
                    .Builder(cache[i].getX(), cache[i - 1].getY())
                    .build();
            }
            else {
                cache[i] = new Coordinate
                    .Builder(cache[i].getX(), cache[i - 1].getY() + 1)
                    .build();
            }

            coor = new Coordinate.Builder(coor.getX(), cache[i].getY()).build();
        }

        for(int i = 0; i < coordinates.length; i++) {
            coordinates[i] = new Coordinate
                .Builder(coordinates[i].getX(), cache[i].getY())
                .r(coordinates[i].getR())
                .build();
        }

        return coor;
    }

    private static Coordinate<Integer, Integer> compressionXBuilder() {
        Coordinate<Integer, Integer> coor = new Coordinate.Builder(0, 0).build();

        for(int i = 1; i < coordinates.length; i++) {
            if (coordinates[i].getX() == coordinates[i - 1].getX()) {
                cache[i] = new Coordinate
                    .Builder(cache[i - 1].getX(), cache[i].getY())
                    .build();
            }
            else {
                cache[i] = new Coordinate
                    .Builder(cache[i - 1].getX() + 1, cache[i].getY()).
                    build();
            }

            coor = new Coordinate.Builder(cache[i].getX(), coor.getY()).build();
        }

        for(int i = 0; i < coordinates.length; i++) {
            coordinates[i] = new Coordinate
                .Builder(cache[i].getX(), coordinates[i].getY())
                .r(coordinates[i].getR())
                .build();
        }

        return coor;
    }
}

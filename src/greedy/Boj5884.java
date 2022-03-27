package greedy;

import common.Coordinate;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 5884번: 감시 카메라
 *
 * @see https://www.acmicpc.net/problem/5884
 *
 */
public class Boj5884 {

    private static final int SIZE = 50_001;

    private static List<Integer>[][] intercepts;
    private static int[][] compressed;

    private static int[] size = new int[2];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Coordinate[] coordinates = new Coordinate[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            coordinates[i] = new Coordinate.Builder(x, y)
                .build();
        }

        init();
        System.out.println(supervise(coordinates));
    }

    /**
     *
     * Supervise
     *
     * line 56 ~ 68: Coordinates compression
     * line 74 ~ 77: make x, y reference pair
     *
     * @param coordinates
     * @return
     */
    private static int supervise(Coordinate<Integer, Integer>[] coordinates) {
        Map<Integer, Integer> x = new HashMap<>();
        int index = 0;
        for(Coordinate<Integer, Integer> coordinate: coordinates){
            if(!x.containsKey(coordinate.getX())) x.put(coordinate.getX(), index++);
            compressed[0][x.get(coordinate.getX())]++;
        }

        Map<Integer, Integer> y = new HashMap<>();
        index = 0;
        for(Coordinate<Integer, Integer> coordinate: coordinates){
            if(!y.containsKey(coordinate.getY())) y.put(coordinate.getY(), index++);
            compressed[1][y.get(coordinate.getY())]++;
        }

        size[0] = x.size();
        size[1] = y.size();
        if(size[0] <= 3 || size[1] <= 3) return 1;

        for(Coordinate<Integer, Integer> coordinate: coordinates){
            intercepts[0][x.get(coordinate.getX())].add(y.get(coordinate.getY()));
            intercepts[1][y.get(coordinate.getY())].add(x.get(coordinate.getX()));
        }

        return matching(0, 1) || matching(1, 0) ? 1: 0;
    }

    /**
     *
     * Matching
     *
     * line 97 ~ 100: find each x, y's interceptor if value is 1, then don't care.
     * line 102: (total - don't care points) is smaller than 2, then cover all with 3 lines.
     *
     * @param index1
     * @param index2
     * @return
     */
    private static boolean matching(int index1, int index2) {
        for(int i = 0; i < size[index1]; i++) {
            int count = 0;

            for(int next: intercepts[index1][i]) {
                if(compressed[index2][next] != 1) continue;
                count++;
            }

            if(size[index2] - count <= 2) return true;
        }

        return false;
    }

    private static void init() {
        intercepts = new ArrayList[2][SIZE];
        compressed = new int[2][SIZE];

        for(int i = 0; i < SIZE; i++){
            intercepts[0][i] = new ArrayList<>();
            intercepts[1][i] = new ArrayList<>();
        }
    }
}

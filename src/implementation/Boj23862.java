package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 23862번: 와이파이
 *
 * @see https://www.acmicpc.net/problem/23862
 *
 */
public class Boj23862 {

    private static WiFi23862[] rooms;
    private static int N;

    private static final String NONE = "IMPOSSIBLE";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        rooms = new WiFi23862[N + 1];
        for(int i = 0; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            rooms[i] = new WiFi23862(new Coordinate23862(x, y), E);
        }

        Arrays
                .stream(Arrays
                        .stream(linkage())
                        .toArray())
                .max()
                .ifPresent(rapid
                        -> System.out.println(rapid == 0 ? NONE: rapid));
    }

    private static int[] linkage() {
        int[] rapids = new int[N + 1];

        for (int src = 1; src <= N; src++) {
            int power = 0;

            for (int snk = 1; snk <= N; snk++) {
                power += Math.max(0
                        , rooms[snk].getEnergy() - manhattanDistance(rooms[src].getCoor(), rooms[snk].getCoor()));
            }

            rapids[src] = Math.max(0
                            , rooms[0].getEnergy() - manhattanDistance(rooms[0].getCoor(), rooms[src].getCoor()) - power);
        }

        return rapids;
    }

    private static int manhattanDistance(Coordinate23862 c1, Coordinate23862 c2) {
        return Math.abs(c1.getX() - c2.getX()) + Math.abs(c1.getY() - c2.getY());
    }
}

class Coordinate23862 {
    private int x;
    private int y;

    public Coordinate23862(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

class WiFi23862 {
    private Coordinate23862 coor;
    private int energy;

    public WiFi23862(Coordinate23862 coor, int energy) {
        this.coor = coor;
        this.energy = energy;
    }

    public Coordinate23862 getCoor() {
        return coor;
    }

    public int getEnergy() {
        return energy;
    }
}

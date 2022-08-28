package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author exponential-e
 * 백준 3089번: 네잎 클로버를 찾아서
 *
 * @see https://www.acmicpc.net/problem/3089
 *
 */
public class Boj3089 {

    private static final HashMap<Character, Coordinate> DIRECTIONS = new HashMap<>();

    private static TreeMap<Integer, TreeSet<Integer>> xFourLeaves = new TreeMap<>();
    private static TreeMap<Integer, TreeSet<Integer>> yFourLeaves = new TreeMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st.nextToken();

        directionMapper();

        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            xFourLeaves.put(X, componentsSettings(xFourLeaves, X, Y));
            yFourLeaves.put(Y, componentsSettings(yFourLeaves, Y, X));
        }

        char[] dir = br.readLine().toCharArray();
        System.out.println(findClovers(dir));
    }

    private static Coordinate findClovers(char[] dirs) {
        Coordinate position = new Coordinate(0, 0);

        for(char dir: dirs) {
            final Coordinate DIRECTION = DIRECTIONS.get(dir);

            if(DIRECTION.getX() == 0) {
                TreeSet<Integer> point = xFourLeaves.get(position.getX());

                Integer y = DIRECTION.getY() > 0 ?
                    point.higher(position.getY()): point.lower(position.getY());
                if(y == null) y = position.getY();

                position = new Coordinate(position.getX(), y);
            }
            else {
                TreeSet<Integer> point = yFourLeaves.get(position.getY());

                Integer x = DIRECTION.getX() > 0 ?
                    point.higher(position.getX()): point.lower(position.getX());
                if(x == null) x = position.getX();

                position = new Coordinate(x, position.getY());
            }
        }

        return position;
    }

    private static TreeSet<Integer> componentsSettings(
        TreeMap<Integer, TreeSet<Integer>> map, int target, int src
    ) {
        TreeSet<Integer> components = map.containsKey(target) ? map.get(target): new TreeSet<>();
        components.add(src);

        return components;
    }

    private static void directionMapper() {
        DIRECTIONS.put('U', new Coordinate(0, 1));
        DIRECTIONS.put('D', new Coordinate(0, -1));
        DIRECTIONS.put('R', new Coordinate(1, 0));
        DIRECTIONS.put('L', new Coordinate(-1, 0));
    }

    private static class Coordinate {
        private static final String SPACE = " ";
        private final int x;
        private final int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return x + SPACE + y;
        }
    }
}

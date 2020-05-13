package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author exponential-e
 * 백준 8911번: 거북이
 *
 * @see https://www.acmicpc.net/problem/8911/
 *
 */
public class Boj8911 {
    private static final String NEW_LINE = "\n";
    private static final int INF = 1_000;

    private static Coordinate start;

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
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            char[] move = br.readLine().toCharArray();
            sb.append(square(move, new Coordinate(0, 0), new Coordinate(0, 0))).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void turtleMove(int mod, int adder){
        switch (mod) {
            case 0:
                start.y += adder;
                break;
            case 1:
                start.x += adder;
                break;
            case 2:
                start.y -= adder;
                break;
            case 3:
                start.x -= adder;
                break;
        }
    }

    private static int square(char[] move, Coordinate min, Coordinate max) {
        start = new Coordinate(0, 0);
        int dir = INF * 2;

        for(char c: move) {
            if(c == 'F') {
                turtleMove(dir % 4, 1);
            }
            else if(c == 'B'){
                turtleMove(dir % 4, -1);
            }
            else {
                switch (c) {
                    case 'R':
                        dir++;
                        break;

                    case 'L':
                        dir--;
                        break;
                }
            }

            min = new Coordinate(Math.min(min.x, start.x), Math.min(min.y, start.y));
            max = new Coordinate(Math.max(max.x, start.x), Math.max(max.y, start.y));
        }

        return (max.x - min.x) * (max.y - min.y);
    }
}

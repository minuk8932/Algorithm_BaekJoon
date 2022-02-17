package math;

import common.Coordinate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2987번: 사과나무
 *
 * @see https://www.acmicpc.net/problem/2987
 *
 */
public class Boj2987 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Coordinate[] triangle = new Coordinate[3];
        for(int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) * 10;
            int y = Integer.parseInt(st.nextToken()) * 10;

            triangle[i] = new Coordinate.Builder(x, y).build();
        }

        int N = Integer.parseInt(br.readLine());
        Coordinate[] appleTree = new Coordinate[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) * 10;
            int y = Integer.parseInt(st.nextToken()) * 10;

            appleTree[i] = new Coordinate.Builder(x, y).build();
        }

        double area = triangleArea(triangle[0], triangle[1], triangle[2]);

        System.out.println(area + "\n" + pointInPolygon(area, triangle, appleTree));
    }

    private static int pointInPolygon(double area, Coordinate[] tri, Coordinate[] at) {
        int count = 0;

        for(Coordinate tree: at) {
            double sum = triangleArea(tree, tri[0], tri[1])
                    + triangleArea(tree, tri[1], tri[2]) + triangleArea(tree, tri[0], tri[2]);

            if(sum != area) continue;
            count++;
        }

        return count;
    }

    private static double triangleArea(Coordinate coor1, Coordinate coor2, Coordinate coor3) {
        return Math.abs(coor1.getX() * (coor2.getY() - coor3.getY()) +
                coor2.getX() * (coor3.getY() - coor1.getY()) +
                coor3.getX() * (coor1.getY() - coor2.getY())) / 200.0;
    }
}

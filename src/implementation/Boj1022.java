package implementation;

import common.Point;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1022번: 소용돌이 이쁘게 출력하기
 *
 * @see https://www.acmicpc.net/problem/1022
 *
 */
public class Boj1022 {

    private static Point<Integer, Integer> from;
    private static Point<Integer, Integer> to;

    private static List<Integer> result = new LinkedList<>();

    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        from = new Point
                .Builder(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))
                .build();

        to = new Point
                .Builder(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))
                .build();

        System.out.println(printing(getLength()));
    }

    private static String printing(int length) {
        StringBuilder sb = new StringBuilder();

        for (int row = from.getRow(); row <= to.getRow(); row++) {
            for (int col = from.getCol(); col <= to.getCol(); col++) {
                int answer = result.remove(0);

                int loop = length - intToLen(answer);
                while(loop-- > 0) {
                    sb.append(SPACE);
                }

                sb.append(answer).append(SPACE);
            }

            sb.append(NEW_LINE);
        }

        return sb.toString();
    }

    private static int intToLen(int value) {
        int len = 0;

        while(value > 0) {
            len++;
            value /= 10;
        }

        return len;
    }

    private static int getLength() {
        int length = 0;

        for (int row = from.getRow(); row <= to.getRow(); row++) {
            for (int col = from.getCol(); col <= to.getCol(); col++) {
                int value = calculator(row, col);
                result.add(value);

                length = Math.max(length, costGet(value));
            }
        }

        return length;
    }

    private static int calculator(int row, int col) {
        int position = Math.max(Math.abs(row), Math.abs(col));

        int difference = position << 1;
        int value = (difference + 1);
        value *= value;

        if (row == position) return value - (position - col);
        value -= difference;

        if (col == -position) return value - (position - row);
        value -= difference;

        if (row == -position) return value - (col + position);
        value -= difference;

        return value - (row + position);
    }

    private static int costGet(int value) {
        return value != 0 ? costGet(value / 10) + 1 : 0;
    }
}

package math;

import java.util.*;
import java.io.*;

/**
 *
 * @author exponential-e
 * 백준 18017번: 총알의 속도
 *
 * @see https://www.acmicpc.net/problem/18017/
 *
 */
public class Boj18017 {
    private static final double c = 299_792_458;

    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        double v1 = Double.parseDouble(st.nextToken());
        double v2 = Double.parseDouble(st.nextToken());

        System.out.println((v1 + v2) / (1 + (v1 * v2) / (c * c)));          // Theory of relativity
    }
}

package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 22251번: 빌런 호석
 *
 * @see https://www.acmicpc.net/problem/22251
 *
 */
public class Boj22251 {

    private static int[][] change = new int[10][10];
    private static boolean[][] light = new boolean[10][7];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        lighting();
        changerRecord();

        System.out.println(swap(N, K, P, X));
    }

    private static int swap(int n, int k, int p, int x) {
        int result = 0;

        List<Integer> src = parsing(x);

        for(int i = 1; i <= n; i++) {
            if(i == x) continue;

            List<Integer> snk = parsing(i);
            int srcLen = src.size();
            int snkLen = snk.size();

            int count = 0;
            int size = k;

            while(size-- > 0) {
                int S = srcLen <= 0 ? 0: src.get(srcLen - 1);
                int T = snkLen <= 0 ? 0: snk.get(snkLen - 1);

                count += change[S][T];
                srcLen--;
                snkLen--;
            }

            if(count <= p) result++;
        }

        return result;
    }

    private static List<Integer> parsing(int loop) {
        List<Integer> list = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();

        while(loop > 0) {
            stack.push(loop % 10);
            loop /= 10;
        }

        while(!stack.isEmpty()) {
            list.add(stack.pop());
        }

        return list;
    }

    private static void changerRecord() {
        for(int i = 0; i < 10; i++) {
            for(int j = i + 1; j < 10; j++) {
                int count = 0;

                for(int k = 0; k < 7; k++) {
                    count += light[i][k] ^ light[j][k] ? 1: 0;
                }

                change[i][j] = change[j][i] = count;
            }
        }
    }

    private static void lighting() {
        for(int i = 0; i < 7; i++) {
            if(i == 3) continue;
            light[0][i] = true;
        }

        light[1][1] = light[1][5] = true;

        for(int i = 0; i < 7; i++) {
            if(i == 2 || i == 5) continue;
            light[2][i] = true;
        }

        for(int i = 0; i < 7; i++) {
            if(i == 2 || i == 4) continue;
            light[3][i] = true;
        }

        for(int i = 0; i < 7; i++) {
            if(i == 0 || i == 4 || i == 6) continue;
            light[4][i] = true;
        }

        for(int i = 0; i < 7; i++) {
            if(i == 1 || i == 4) continue;
            light[5][i] = true;
        }

        for(int i = 0; i < 7; i++) {
            if(i == 1) continue;
            light[6][i] = true;
        }

        light[7][0] = light[7][1] = light[7][5] = true;

        for(int i = 0; i < 7; i++) {
            light[8][i] = true;
        }

        for(int i = 0; i < 7; i++) {
            if(i == 4) continue;
            light[9][i] = true;
        }
    }
}

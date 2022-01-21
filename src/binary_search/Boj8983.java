package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 8983번: 사냥꾼
 *
 * @see https://www.acmicpc.net/problem/8983
 *
 */
public class Boj8983 {

    private static int[] launch;
    private static Animal8983[] animals;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        launch = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            launch[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(launch);

        animals = new Animal8983[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            animals[i] = new Animal8983(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        System.out.println(hunting(L));
    }

    /**
     *
     * Binary search
     *
     * line 63 ~ 66: in range animals
     * line 68 ~ 73: pad position search
     *
     * @param target
     * @return
     */
    private static int hunting(int target) {
        int answer = 0;

        for(Animal8983 ani: animals) {
            int start = 0;
            int end = launch.length - 1;

            while(start <= end) {
                int mid = (start + end) >> 1;

                if(manhattanDistance(ani, launch[mid]) <= target) {
                    answer++;
                    break;
                }

                if(ani.getX() > launch[mid]) {
                    start = mid + 1;
                }
                else {
                    end = mid - 1;
                }
            }
        }

        return answer;
    }

    private static int manhattanDistance(Animal8983 one, int x) {
        return Math.abs(one.getX() - x) + one.getY();
    }
}

class Animal8983 {
    private int x;
    private int y;

    public Animal8983(int x, int y) {
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
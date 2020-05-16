package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14604번: Over fitting (small)
 *
 * @see https://www.acmicpc.net/problem/14604/
 *
 */
public class Boj14604 {
    private static final String FAN = "LOVELYZ";
    private static ArrayList<Coordinate> fan = new ArrayList<>();
    private static ArrayList<Coordinate> notFan = new ArrayList<>();

    private static int result;

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
        int N = Integer.parseInt(br.readLine());

        while(N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(FAN.equals(st.nextToken())) fan.add(new Coordinate(x, y));
            else notFan.add(new Coordinate(x, y));
        }

        makeLovelyz(notFan, notFan);                                       // considering all cases
        makeLovelyz(fan, notFan);

        System.out.println(result);
    }

    private static void makeLovelyz(ArrayList<Coordinate> f, ArrayList<Coordinate> nf) {
        int size = f.size();
        int nSize = nf.size();
        int[] sizes = {fan.size(), notFan.size()};

        boolean[][] used = new boolean[size][nSize];

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < nSize; j++) {                            // make set with fan fan, fan notFan, notFan notFan
                if(used[i][j]) continue;
                used[i][j] = true;

                Coordinate current = f.get(i);
                Coordinate another = nf.get(j);

                if(isSame(current, another)) continue;

                int b = another.x - current.x;                          // get a, b, c
                int a = current.y - another.y;
                int c = a * current.x + b * current.y;

                int[] fans = new int[2];
                for(int k = 0; k < sizes[0]; k++) {                     // location relation checker
                    Coordinate target = fan.get(k);

                    if(online(target, a, b, c) >= 0) fans[0]++;
                    if(online(target, a, b, c) <= 0) fans[1]++;
                }

                int[] notFans = new int[2];
                for(int k = 0; k < sizes[1]; k++) {
                    Coordinate target = notFan.get(k);

                    if(online(target, a, b, c) > 0) notFans[0]++;
                    if(online(target, a, b, c) < 0) notFans[1]++;
                }

                if(notFans[0] * notFans[1] != 0) continue;
                if(notFans[0] == 0) result = Math.max(fans[0], result);
                if(notFans[1] == 0) result = Math.max(fans[1], result);
            }
        }
    }

    private static boolean isSame(Coordinate c1, Coordinate c2) {
        return c1.x == c2.x && c1.y == c2.y;
    }

    private static int online(Coordinate target, int a, int b, int c) {
        return a * target.x + b * target.y - c;
    }
}

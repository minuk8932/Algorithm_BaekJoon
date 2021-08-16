package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 22945번: 팀 빌딩
 *
 * @see https://www.acmicpc.net/problem/22945
 *
 */
public class Boj22945 {

    private static class Level{
        int lev;
        int idx;

        public Level(int lev, int idx) {
            this.lev = lev;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = PARSE.apply(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        Level[] developer = ARRAY.apply(N);
        for(int i = 0; i < N; i++) {
            developer[i] = new Level(PARSE.apply(st.nextToken()), i);
        }

        Arrays.sort(developer, (x, y) -> {
            if(x.lev == y.lev) return y.idx - x.idx;
            return y.lev - x.lev;
        });

        System.out.println(teamBuilding(developer));
    }

    /**
     *
     * Team Building
     *
     * line 59 ~ 65: except the biggest value, find index what is most separated.
     *
     * @param dev
     * @return
     */
    private static int teamBuilding(Level[] dev) {
        int answer = 0;
        int max = dev[0].idx;
        int min = dev[0].idx;

        for(int i = 1; i < dev.length; i++) {

            answer = Math.max(answer, dev[i].lev * (Math.abs(max - dev[i].idx) - 1));
            answer = Math.max(answer, dev[i].lev * (Math.abs(min - dev[i].idx) - 1));

            max = Math.max(max, dev[i].idx);
            min = Math.min(min, dev[i].idx);

        }

        return answer;
    }

    private static final Function<String, Integer> PARSE = Integer::parseInt;
    private static final Function<Integer, Level[]> ARRAY = Level[]::new;

}

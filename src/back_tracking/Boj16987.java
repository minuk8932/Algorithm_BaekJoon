package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 16987번: 계란으로 계란치기
 *
 * @see https://www.acmicpc.net/problem/16987
 *
 */
public class Boj16987 {

    private static int max;
    private static Egg[] eggs;

    private static class Egg {
        int durability;
        int weight;

        public Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = PARSE.apply(br.readLine());

        eggs = new Egg[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            eggs[i] = new Egg(PARSE.apply(st.nextToken()), PARSE.apply(st.nextToken()));
        }

        recursion(0);
        System.out.println(max);
    }

    /**
     *
     * Recursion
     *
     * line 66 ~ 68: in hand broken
     * line 86: broken nothing
     *
     * @param current
     */
    private static void recursion(int current) {
        if (current == eggs.length) {
            int broken = 0;

            for (int i = 0; i < eggs.length; i++) {
                if (eggs[i].durability > 0) continue;
                broken++;
            }

            max = Math.max(broken, max);
            return;
        }

        if (eggs[current].durability <= 0){
            recursion(current + 1);
            return;
        }

        boolean flag = true;

        for (int next = 0; next < eggs.length; next++) {
            if (!HITTABLE.test(current, next)) continue;
            flag = false;

            eggs[next].durability -= eggs[current].weight;
            eggs[current].durability -= eggs[next].weight;

            recursion(current + 1);

            eggs[next].durability += eggs[current].weight;
            eggs[current].durability += eggs[next].weight;
        }

        if(flag) recursion(eggs.length);
    }

    private static final Function<String, Integer> PARSE = Integer::parseInt;

    private static final BiPredicate<Integer, Integer> HITTABLE = (x, y) ->
            x != y && eggs[y].durability > 0;

}

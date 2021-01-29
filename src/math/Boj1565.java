package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1565번: 수학
 *
 * @see https://www.acmicpc.net/problem/1565
 *
 */
public class Boj1565 {

    private static HashMap<Integer, Integer> lcm = new HashMap<>();
    private static HashMap<Integer, Integer> gcd = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int D = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        lcm = primeFactors(Integer.parseInt(st.nextToken()));
        while(D-- > 1) {
            lcm = getLcm(lcm, primeFactors(Integer.parseInt(st.nextToken())));
        }

        st = new StringTokenizer(br.readLine());
        int G = Integer.parseInt(st.nextToken());
        while(M-- > 1) {
            G = GCD(G, Integer.parseInt(st.nextToken()));
        }

        gcd = primeFactors(G);
        System.out.println(finder());
    }

    /**
     *
     * find lcm partition by prime factors
     *
     * @param map1
     * @param map2
     * @return
     */
    private static HashMap<Integer, Integer> getLcm(HashMap<Integer, Integer> map1, HashMap<Integer, Integer> map2){
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int key: map1.keySet()) {
            if(!map2.containsKey(key)) map.put(key, map1.get(key));
            else map.put(key, Math.max(map1.get(key), map2.get(key)));
        }

        for(int key: map2.keySet()) {
            if(map1.containsKey(key)) continue;
            map.put(key, map2.get(key));
        }

        return map;
    }

    /**
     *
     * using gcd prime factors & lcm prime factors
     *
     * line 77: if lcm factors is bigger than gcd ? have any factors for result
     *
     * @return
     */
    private static long finder() {
        for(int factor: lcm.keySet()) {
            if(!gcd.containsKey(factor)) return 0;

            gcd.put(factor, gcd.get(factor) - lcm.get(factor));
            if(gcd.get(factor) < 0) return 0;
        }

        long result = 1L;
        for(int factor: gcd.keySet()) {
            result = result * (gcd.get(factor) + 1);
        }

        return result;
    }

    /**
     *
     * prime factors getter
     *
     * line 101 ~ 106 find factors of each target
     *
     * @param target
     * @return
     */
    private static HashMap<Integer, Integer> primeFactors(int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 2; i * i <= target; i++) {
            while (target % i == 0) {
                map.merge(i, 1, Integer::sum);
                target /= i;
            }
        }

        if (target > 1) map.merge(target, 1, Integer::sum);
        return map;
    }

    private static int GCD(int a, int b) {
        if(a == 0) return b;
        return GCD(b % a, a);
    }
}

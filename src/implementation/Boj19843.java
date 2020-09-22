package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 19843번: 수면 패턴
 *
 * @see https://www.acmicpc.net/problem/19843
 *
 */
public class Boj19843 {
    private static final HashMap<String, Integer> DAY = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        init();
        int total = 0;
        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int D1 = DAY.get(st.nextToken());
            int H1 = Integer.parseInt(st.nextToken());
            int D2 = DAY.get(st.nextToken());
            int H2 = Integer.parseInt(st.nextToken());

            total += checkTime(D1, D2, H1, H2);
        }

        int result = T - total;
        System.out.println(result <= 48 ? result <= 0 ? 0: result: -1);
    }

    private static int checkTime(int d1, int d2, int h1, int h2) {
        int diff = d2 - d1;

        if(diff == 0) return h2 - h1;
        else return diff * 24 + h2 - h1;
    }

    private static void init() {
        DAY.put("Mon", 0);
        DAY.put("Tue", 1);
        DAY.put("Wed", 2);
        DAY.put("Thu", 3);
        DAY.put("Fri", 4);
    }
}

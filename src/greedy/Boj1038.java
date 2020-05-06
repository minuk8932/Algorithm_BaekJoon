package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author exponential-e
 * 백준 1038번: 감소하는 수
 *
 * @see https://www.acmicpc.net/problem/1038/
 *
 */
public class Boj1038 {
    private static ArrayList<Long> desList = new ArrayList<>();
    private static long[] bigNumber = {876_543_210, 976_543_210, 986_543_210, 987_543_210, 987_643_210
            , 987_653_210, 987_654_210, 987_654_310, 987_654_320, 987_654_310, 9_876_543_210L};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        init();         // pre find
        System.out.println(N >= desList.size() ? -1: desList.get(N));
    }

    private static void init() {
        for(long i = 0; i < 10; i++) {
            desList.add(i);
        }

        long val = 10;

        while(val <= 98_765_432){
            long loop = val;
            int first = -1;

            boolean flag = true;

            while(loop > 0) {
                int mod = (int) (loop % 10);

                if(mod <= first) {
                    flag = false;
                    break;
                }

                first = mod;
                loop /= 10;
            }

            if (flag) desList.add(val);
            val++;
        }

        for(long big: bigNumber) {
            desList.add(big);
        }
    }
}

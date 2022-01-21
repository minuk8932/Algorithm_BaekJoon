package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 24268번: 2022는 무엇이 특별할까?
 *
 * @see https://www.acmicpc.net/problem/24268
 *
 */
public class Boj24268 {

    private static List<Integer> permutation = new ArrayList<>();

    private static boolean[] visit;
    private static int d;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        caseWork();
        System.out.println(special(N));
    }

    private static int special(int target) {
        for(int per: permutation) {
            int decimal = dnaryToDecimal(d, per);
            if(decimal > target) return decimal;
        }

        return -1;
    }

    private static int dnaryToDecimal(int dnary, int value) {
        int loop = dnary;
        int pow = 1;

        int answer = 0;

        while(loop-- > 0) {
            answer += (pow * (value % 10));

            pow *= dnary;
            value /= 10;
        }

        return answer;
    }

    private static void caseWork() {
        for(int i = 1; i < d; i++) {
            visit = new boolean[d];
            backTracking(i, i, 0);
        }
    }

    private static void backTracking(int current, int value, int count) {
        if(count == d - 1) {
            permutation.add(value);
            return;
        }

        visit[current] = true;

        for(int next = 0; next < d; next++) {
            if(visit[next]) continue;

            backTracking(next, value * 10 + next, count + 1);
            visit[next] = false;
        }
    }
}

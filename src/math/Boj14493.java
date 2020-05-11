package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14493번: 과일 노리
 *
 * @see https://www.acmicpc.net/problem/14493/
 *
 */
public class Boj14493 {
    private static class Bot {
        int appear;
        int supervise;

        public Bot(int appear, int supervise) {
            this.appear = appear;
            this.supervise = supervise;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Bot[] bot = new Bot[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            bot[i] = new Bot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        System.out.println(access(bot));
    }

    private static int access(Bot[] bot) {
        int result = 0;

        for(Bot b: bot) {
            int sum = b.appear + b.supervise;
            int mod = result % sum;

            if(mod >= 0 && mod < b.supervise) {         // patrol time, wait
                int val = result / sum;
                result = val * sum + b.supervise;
            }

            result++;
        }

        return result;
    }
}

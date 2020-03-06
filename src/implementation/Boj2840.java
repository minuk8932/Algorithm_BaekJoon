package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2840번: 행운의 바퀴
 *
 * @see https://www.acmicpc.net/problem/2840/
 *
 */
public class Boj2840 {
    private static final char EMPTY = ' ';
    private static final String NOT = "!";
    private static final String UNKNOWN = "?";

    private static class Pair{
        char word;
        int time;

        public Pair(int time, char word){
            this.word = word;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Pair[] p = new Pair[K];
        for(int i = K - 1; i >= 0; i--){
            st = new StringTokenizer(br.readLine());
            p[i] = new Pair(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
        }

        System.out.println(luckyStrike(N, p));
    }

    private static String luckyStrike(int n, Pair[] pairs){
        char[] wheel = new char[n];
        int idx = 0;

        Arrays.fill(wheel, EMPTY);

        for(Pair p: pairs){
            if(wheel[idx] != EMPTY){                // same alphabet same position
                if(wheel[idx] == p.word) {
                    idx = (idx + p.time) % n;
                    continue;
                }

                return NOT;
            }

            wheel[idx] = p.word;                    // placed
            idx = (idx + p.time) % n;               // find index
        }

        StringBuilder sb = new StringBuilder();
        boolean[] used = new boolean[26];

        for(int i = 0; i < wheel.length; i++){
            if(wheel[i] == EMPTY){
                sb.append(UNKNOWN);
                continue;
            }

            if(used[wheel[i] - 'A']) return NOT;
            used[wheel[i] - 'A'] = true;

            sb.append(wheel[i]);
        }

        return sb.toString();
    }
}

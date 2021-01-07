package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20529번: 가장 가까운 세 사람 사이의 심리적 거리
 *
 * @see https://www.acmicpc.net/problem/20529
 *
 */
public class Boj20529 {

    private static final String NEW_LINE = "\n";
    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            char[][] mbti = new char[N][];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                mbti[i] = st.nextToken().toCharArray();
            }

            sb.append(psychicalDistance(mbti)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int psychicalDistance(char[][] mbti) {
        if(mbti.length >= 33) return 0;                     // according to The Principles of Pigeons
        int min = INF;

        for(int x = 0; x < mbti.length; x++) {
            for(int y = x + 1; y < mbti.length; y++) {
                for(int z = y + 1; z < mbti.length; z++) {
                    min = Math.min(min,
                            getDistance(mbti[x], mbti[y]) + getDistance(mbti[y], mbti[z]) + getDistance(mbti[z], mbti[x]));
                }
            }
        }

        return min;
    }

    private static int getDistance(char[] a, char[] b) {
        int count = 0;

        for(int i = 0; i < 4; i++) {
            if(a[i] == b[i]) continue;
            count++;
        }

        return count;
    }
}

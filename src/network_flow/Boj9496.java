package network_flow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author exponential-e
 * 백준 9496번: 조 나누기
 *
 * @see https://www.acmicpc.net/problem/9496
 *
 */
public class Boj9496 {

    private static boolean[] visit;
    private static boolean[][] hate;

    private static int[] grade;
    private static int[] aMatch, bMatch;

    private static ArrayList<Integer>[] connection;

    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        visit = new boolean[N];
        hate = new boolean[N][N];

        grade = new int[N];
        int idx = 0;
        for(char g: br.readLine().toCharArray()) {
            grade[idx++] = g - '0';
        }

        for(int i = 0; i < N; i++) {
            String input = br.readLine();

            for(int j = 0; j < N; j++) {
                hate[i][j] = input.charAt(j) == 'Y';
            }
        }

        int min = bipartiteMatch(1, 2);
        min = Math.min(bipartiteMatch(1, 3), min);
        System.out.println(N - Math.min(min, bipartiteMatch(2, 3)));
    }

    /**
     *
     * Bipartite Match
     *
     * line 63: initialization
     * line 69: find matching by recursion
     *
     * @param S
     * @param T
     * @return
     */
    private static int bipartiteMatch(int S, int T) {
        int result = 0;
        init(S, T);

        for(int i = 0; i < N; i++){
            if(grade[i] != S) continue;
            visit = new boolean[N];
            result += recursion(i);
        }

        return result;
    }

    /**
     *
     * Matching recursion
     *
     * line 88: next b not matching yet or not visited and next b matched
     *
     * @param current
     * @return
     */
    private static int recursion(int current) {
        visit[current] = true;

        for(int next: connection[current]){
            if(bMatch[next] == -1 || (!visit[bMatch[next]] && recursion(bMatch[next]) == 1)){
                aMatch[current] = next;
                bMatch[next] = current;

                return 1;
            }
        }

        return 0;
    }

    private static void init(int S, int T){
        aMatch = new int[N];
        bMatch = new int[N];
        connection = new ArrayList[N];

        for(int i = 0; i < connection.length; i++){
            connection[i] = new ArrayList<>();
            aMatch[i] = bMatch[i] = -1;
        }

        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grade[i] == S && grade[j] == T && hate[i][j]) connection[i].add(j);
            }
        }
    }
}

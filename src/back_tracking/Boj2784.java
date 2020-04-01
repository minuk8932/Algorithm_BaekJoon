package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author exponential-e
 * 백준 2784번: 가로 세로 퍼즐
 *
 * @see https://www.acmicpc.net/problem/2784/
 *
 */
public class Boj2784 {
    private static String[] word  = new String[6];
    private static boolean[] visit = new boolean[6];
    private static ArrayList<Integer> perm = new ArrayList<>();

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < word.length; i++){
            word[i] = br.readLine();
        }

        for(int i = 1; i < 7; i++) {            // make puzzle permutation
            visit = new boolean[6];
            backTracking(i, 0, i);
        }

        System.out.println(puzzle());
    }

    private static void backTracking (int current, int count, int val){
        if(count == 2) {
            perm.add(val);
            return;
        }

        visit[current - 1] = true;

        for(int next = 1; next < 7; next++){
            if(visit[next - 1]) continue;

            backTracking(next, count + 1, val * 10 + next);
            visit[next - 1] = false;
        }
    }

    private static String puzzle (){
        ArrayList<String> candidate = new ArrayList<>();

        for(int p: perm) {
            int loop = p;

            String[] puzzle = new String[3];
            int i = 0;

            while(loop > 0){
                int idx = (loop % 10) - 1;
                puzzle[i++] = word[idx];

                loop /= 10;
            }

            if(!judgement(puzzle)) continue;                            // is correct ?

            StringBuilder builder = new StringBuilder();
            for(i = 0; i < puzzle.length; i++){
                builder.append(puzzle[i]);
            }

            candidate.add(builder.toString());
        }

        Collections.sort(candidate);

        StringBuilder sb = new StringBuilder();
        if (candidate.size() == 0) return sb.append(0).toString();      // any puzzle

        String result = candidate.get(0);

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                sb.append(result.charAt(i * 3 + j));
            }

            sb.append(NEW_LINE);
        }

        return sb.toString();
    }

    private static boolean judgement(String[] arr){
        String[] comp = new String[6];
        for(int i = 0; i < 3; i++){
            comp[i] = arr[i];
        }

        int index = 0;

        for(int i = 3; i < 6; i++){
            StringBuilder build = new StringBuilder();

            for(int x = 0; x < 3; x++){                         // make 3 by 3 puzzle
                build.append(arr[x].charAt(index));
            }

            comp[i] = build.toString();
            index++;
        }

        Arrays.sort(comp);

        for(int i = 0; i < 6; i++){
            if(!comp[i].equals(word[i])) return false;
        }

        return true;
    }
}

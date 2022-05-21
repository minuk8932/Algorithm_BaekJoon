package implementation;

import common.Pair;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25181번: Swap the elements
 *
 * @see https://www.acmicpc.net/problem/25181
 *
 */
public class Boj25181 {

    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(swapable(A) ? printOne(A): -1);
    }

    private static String printOne(int[] one) {
        ArrayList<Pair<Integer>> pairs = new ArrayList<>();
        for(int i = 0; i < one.length; i++) {
            pairs.add(new Pair.Builder(i, one[i]).build());
        }

        Collections.sort(pairs, Comparator.comparingInt(Pair::getSecond));

        int size = pairs.size();
        int[] answer = new int[size];
        int idx = size >> 1;

        for(Pair<Integer> p: pairs) {
            idx %= size;
            answer[p.getFirst()] = pairs.get(idx++).getSecond();
        }

        StringBuilder sb = new StringBuilder();
        for(int a: answer) {
            sb.append(a).append(SPACE);
        }

        return sb.toString();
    }

    private static boolean swapable(int[] a) {
        int[] count = new int[100_001];
        for(int v: a) {
            count[v]++;
        }

        int half = a.length >> 1;
        for(int c: count) {
            if(c > half) return false;
        }

        return true;
    }
}

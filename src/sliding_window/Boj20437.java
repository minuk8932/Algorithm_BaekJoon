package sliding_window;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author exponential-e
 * 백준 20437번: 문자열 게임2
 *
 * @see https://www.acmicpc.net/problem/20437
 *
 */
public class Boj20437 {

    private static ArrayList<Integer>[] alpha;
    private static LinkedList<Integer> window;

    private static final int INF = 1_000_001;
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            char[] W = br.readLine().toCharArray();
            int K = Integer.parseInt(br.readLine());

            alpha = new ArrayList[26];
            for(int i = 0; i < 26; i++) {
                alpha[i] = new ArrayList<>();
            }

            process(W, K);
            if(window.size() == 0){
                sb.append(-1).append(NEW_LINE);
                continue;
            }

            int shortest = INF;
            int longest = 0;

            while(!window.isEmpty()){
                int value = window.poll();

                shortest = Math.min(shortest, value);
                longest = Math.max(longest, value);
            }

            sb.append(shortest).append(SPACE).append(longest).append(NEW_LINE);
        }

        System.out.print(sb.toString());
    }

    /**
     *
     * save alpha data with index
     *
     * line 78 ~ 80: check data count & string size
     *
     * @param arr
     * @param k
     */
    private static void process (char[] arr, int k){
        for(int i = 0; i < arr.length; i++) {
            alpha[arr[i] - 'a'].add(i);
        }

        window = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            int size = alpha[i].size();
            if (size < k) continue;

            for (int idx = 0; idx < size - k + 1; idx++) {
                window.offerLast(alpha[i].get(idx + k - 1) - alpha[i].get(idx) + 1);
            }
        }
    }
}

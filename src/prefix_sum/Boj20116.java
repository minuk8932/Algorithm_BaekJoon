package prefix_sum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20116번: 박스의 균형
 *
 * @see https://www.acmicpc.net/problem/20116
 *
 */
public class Boj20116 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long L = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long[] boxes = new long[n];
        for(int i = 0; i < n; i++) {
            boxes[i] = Long.parseLong(st.nextToken());
        }

        System.out.println(isStable(L, boxes));
    }

    private static String isStable(long l, long[] boxes) {
        long target = boxes[boxes.length - 1];
        double count = 1;

        for(int i = boxes.length - 2; i >= 0; i--) {
            if(boxes[i] - l >= target / count || target / count >= boxes[i] + l) return "unstable";
            target += boxes[i];
            count++;
        }

        return "stable";
    }
}

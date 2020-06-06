package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;

/**
 *
 * @author exponential-e
 * 백준 2957번: 이진 탐색 트리
 *
 * @see https://www.acmicpc.net/problem/2957/
 *
 */
public class Boj2957 {
    private static TreeMap<Integer, Long> map = new TreeMap<>();
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long C = 0;

        StringBuilder sb = new StringBuilder();
        sb.append(C).append(NEW_LINE);
        map.put(Integer.parseInt(br.readLine()), 0L);                       // first value

        while(N-- > 1) {
            int node = Integer.parseInt(br.readLine());
            int right = -1, left = -1;

            if(map.higherKey(node) != null) right = map.higherKey(node);    // find right most
            if(map.lowerKey(node) != null) left = map.lowerKey(node);       // find left most

            long level;

            if(right == -1) {                       // decision next level
                level = map.get(left) + 1;
                map.put(node, level);
            }
            else if(left == -1) {
                level = map.get(right) + 1;
                map.put(node, level);
            }
            else {
                long upper = map.get(right);
                long lower = map.get(left);

                map.put(node, level = upper > lower ? upper + 1 : lower + 1);
            }

            C += level;
            sb.append(C).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }
}

package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;

/**
 *
 * @author exponential-e
 * 백준 1539번: 이진 검색 트리
 *
 * @see https://www.acmicpc.net/problem/1539/
 *
 */
public class Boj1539 {
    private static TreeMap<Integer, Long> map = new TreeMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long level = 1;
        map.put(Integer.parseInt(br.readLine()), level);                       // first value

        while(N-- > 1) {
            int node = Integer.parseInt(br.readLine());
            int right = -1, left = -1;

            if(map.higherKey(node) != null) right = map.higherKey(node);    // find right most
            if(map.lowerKey(node) != null) left = map.lowerKey(node);       // find left most

            long current;

            if(right == -1) {                                               // decision next level
                current = map.get(left) + 1;
                map.put(node, current);
            }
            else if(left == -1) {
                current = map.get(right) + 1;
                map.put(node, current);
            }
            else {
                long upper = map.get(right);
                long lower = map.get(left);

                map.put(node, current = upper > lower ? upper + 1 : lower + 1);
            }

            level += current;
        }

        System.out.println(level);
    }
}

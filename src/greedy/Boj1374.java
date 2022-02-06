package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 *
 * @author exponential-e
 * 백준 1374번: 강의실
 *
 * @see https://www.acmicpc.net/problem/1374
 *
 */
public class Boj1374 {

    private static Map<Integer, Integer> lectures = new TreeMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        while(N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            lectures.merge(s, 1, Integer::sum);
            lectures.merge(e, -1, Integer::sum);
        }

        System.out.println(countRooms());
    }

    private static int countRooms() {
        int max = 0;
        int count = 0;

        for(Map.Entry<Integer, Integer> lecture: lectures.entrySet()) {
            count += lecture.getValue();
            max = Math.max(count, max);
        }

        return max;
    }
}

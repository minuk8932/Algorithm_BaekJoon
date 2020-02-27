package depth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11812번: K진 트리
 *
 * @see https://www.acmicpc.net/problem/11812/
 *
 */
public class Boj11812 {
    private static long N, x, y;
    private static long node1, node2;
    private static int K;
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        while(Q-- > 0){
            st = new StringTokenizer(br.readLine());
            node1 = Long.parseLong(st.nextToken());
            node2 = Long.parseLong(st.nextToken());

            sb.append(find()).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static long find(){
        if(K == 1) return Math.abs(node1 - node2);
        long result = 0;

        x = finder(node1);                              // get depth
        y = finder(node2);

        result += getResult(x, y, node1, true);    // set level
        result += getResult(y, x, node2, false);

        while (node1 != node2) {
            node1 = (node1 + K - 2) / K;                // find common root
            node2 = (node2 + K - 2) / K;
            result += 2;
        }

        return result;
    }

    private static long getResult(long d1, long d2, long a, boolean flag){
        long count = 0;

        while(d1 > d2){
            a = (a + K - 2) / K;
            d1--;
            count++;
        }

        if(flag){
            x = d1;
            node1 = a;
        }
        else{
            y = d1;
            node2 = a;
        }

        return count;
    }

    private static long finder(long target){
        long count = 1;
        long level = 0;

        while (target > 0) {
            target -= count;
            count *= K;
            level++;
        }

        return level;
    }
}

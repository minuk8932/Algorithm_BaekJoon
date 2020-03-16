package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11735번: 정산소
 *
 * @see https://www.acmicpc.net/problem/11735/
 *
 */
public class Boj11735 {
    private static boolean[] rowVisit = new boolean[1_000_001];
    private static boolean[] colVisit = new boolean[1_000_001];
    private static long row, col;
    private static long[] count = new long[2];

    private static final String NEW_LINE = "\n";
    private static final String ROW = "R";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        while(q-- > 0){
            st = new StringTokenizer(br.readLine());
            String query = st.nextToken();
            int idx = Integer.parseInt(st.nextToken());

            long total = n * (n + idx * 2 + 1) / 2;                              // total sum
            if(query.equals(ROW)){
                if(rowVisit[idx]){
                    sb.append(0).append(NEW_LINE);
                    continue;
                }

                sb.append(total - (col + count[1] * idx)).append(NEW_LINE);     // except
                rowVisit[idx] = true;                                            // used
                row += idx;
                count[0]++;
            }
            else {
                if(colVisit[idx]){
                    sb.append(0).append(NEW_LINE);
                    continue;
                }

                sb.append(total - (row + count[0] * idx)).append(NEW_LINE);
                colVisit[idx] = true;
                col += idx;
                count[1]++;
            }
        }

        System.out.println(sb.toString());
    }
}

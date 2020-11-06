import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14730번: 謎紛芥索紀 (Small)
 *
 * @see https://www.acmicpc.net/problem/14730
 *
 */
public class Boj14730 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int result = 0;

        while(N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int C = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            result += C * K;
        }

        System.out.println(result);
    }
}

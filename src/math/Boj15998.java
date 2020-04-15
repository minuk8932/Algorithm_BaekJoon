package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 15998번: 카카오머니
 *
 * @see https://www.acmicpc.net/problem/15998/
 *
 */
public class Boj15998 {

    private static class Log {
        long tried;
        long result;

        public Log(long tried, long result){
            this.tried = tried;
            this.result = result;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Log[] query = new Log[N + 1];
        query[0] = new Log(0, 0);
        long gcd = 0;

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            query[i] = new Log(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));

            gcd = gcd(gcd, query[i].result - query[i - 1].result - query[i].tried);         // make deposit
        }

        System.out.println(validation(query, gcd));
    }

    private static long validation(Log[] query, long G) {
        if(G == 0) return 1;

        for(int i = 1; i < query.length; i++) {
            long diff = query[i].result - query[i - 1].result;
            if (diff == query[i].tried) continue;                               // valid result .... 1)

            if (G <= query[i].result) return -1;                                // gcd is smaller than result
            if (query[i].tried == 0) continue;

            if (query[i].tried > 0){                                            // not valid if deposit
                return -1;
            }
            else {
                if(query[i - 1].result + query[i].tried > 0) return -1;         // withdraw is bigger than before result, cause 1)
            }
        }

        return G;
    }

    private static long gcd(long a, long b) {
        if(a == 0) return b;
        return gcd(b % a, a);
    }
}

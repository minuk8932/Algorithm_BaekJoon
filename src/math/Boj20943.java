package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20943번: 카카오톡
 *
 * @see https://www.acmicpc.net/problem/20943
 *
 */
public class Boj20943 {

    private static final long CIPHER = 1_000_000_000L;

    private static class User {
        long a;
        long b;
        long c;

        public User(long a, long b, long c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public long gcd(long a, long b) {
            if(b == 0) return a;
            return gcd(b, a % b);
        }

        private long makeKey(long g) {
            return CIPHER * (this.a / g + CIPHER) + (this.b / g + CIPHER);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        User[] users = new User[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            users[i] = new User(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        System.out.println(findUserPair(N, users));
    }

    /**
     *
     * Find user pairs
     *
     * line 69 ~ 72: make key & counting
     * line 79 ~ 81: except complementary set
     *
     * @param n
     * @param users
     * @return
     */
    private static long findUserPair(long n, User[] users) {
        HashMap<Long, Long> parallel = new HashMap<>();

        for(User u: users) {
            long g = u.gcd(u.a, u.b);
            long key = u.makeKey(g);

            parallel.merge(key, 1L, Long::sum);
        }

        if(parallel.size() == 1) return 0;

        long result = n * (n - 1) / 2L;

        for(Map.Entry<Long, Long> count: parallel.entrySet()) {
            result -= count.getValue() * (count.getValue() - 1) / 2L;
        }


        return result;
    }
}

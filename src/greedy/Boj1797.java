package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1797번: 균형잡힌 줄서기
 *
 * @see https://www.acmicpc.net/problem/1797/
 *
 */
public class Boj1797 {
    private static final int INF = 1_000_000;

    private static class Member implements Comparable<Member>{
        int x;
        int gender;

        public Member(int x, int gender) {
            this.x = x;
            this.gender = gender;
        }

        @Override
        public int compareTo(Member m) {
            return this.x < m.x ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Member[] members = new Member[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            members[i] = new Member(x, gender == 0 ? 1: -1);
        }

        Arrays.sort(members);
        System.out.println(makeGroup(N, members));
    }

    private static int makeGroup(int n, Member[] mem) {
        if (n == 2) return -mem[0].x + mem[1].x;

        int size = 0;
        int validator = 0;

        int[] visit = new int[INF * 2];
        Arrays.fill(visit, -1);

        for(int i = 0; i < n; i++) {
            validator += mem[i].gender;

            if(visit[validator + INF] == -1) visit[validator + INF] = i;                // first appearance
            else size = Math.max(size, mem[i].x - mem[visit[validator + INF] + 1].x);   // already appearance
        }

        return size;
    }
}

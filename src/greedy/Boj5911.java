package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 5911번: 선물
 *
 * @see https://www.acmicpc.net/problem/5911
 *
 */
public class Boj5911 {

    private static class Package implements Comparable<Package>{
        long present;
        long send;

        public Package(long present, long send) {
            this.present = present;
            this.send = send;
        }

        @Override
        public int compareTo(Package p) {
            return this.send + this.present < p.present + p.send ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        Package[] pack = new Package[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pack[i] = new Package(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        }

        System.out.println(DHL(B, pack));
    }

    /**
     *
     * Greedy sending
     *
     * line 67: select one, using coupon
     * line 72 ~ 78: find max count
     *
     * @param billionaire
     * @param packs
     * @return
     */
    private static int DHL (long billionaire, Package[] packs) {
        int max = 0;

        for(int i = 0; i < packs.length; i++) {
            Package[] dummy = new Package[packs.length];

            for(int j = 0; j < packs.length; j++) {
                dummy[j] = packs[j];
            }

            dummy[i] = new Package(dummy[i].present / 2, dummy[i].send);
            Arrays.sort(dummy);

            long total = billionaire;
            int count = 0;
            for(Package dum: dummy) {
                long cost = dum.present + dum.send;

                if(total < cost) break;
                count++;
                total -= cost;
            }

            max = Math.max(max, count);
        }


        return max;
    }
}

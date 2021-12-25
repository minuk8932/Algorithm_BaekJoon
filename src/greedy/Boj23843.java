package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 23843번: 콘센트
 *
 * @see https://www.acmicpc.net/problem/23843
 *
 */
public class Boj23843 {

    private static PriorityQueue<Long> device = new PriorityQueue<>(Comparator.comparingLong(dev -> -dev));

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N;i ++) {
            device.offer(Long.parseLong(st.nextToken()));
        }

        System.out.println(charging(M));
    }

    /**
     *
     * Charging
     *
     * line 46: get target value
     *
     * @param consentCount
     * @return
     */
    private static long charging(int consentCount) {
        long time = 0;

        while(!device.isEmpty()) {
            long target = device.poll();

            inAndOut(consentCount - 1, target);
            time += target;
        }

        return time;
    }

    /**
     *
     * InAndOut
     *
     * line 68 ~ 73: fill in
     *
     * @param loop
     * @param snk
     */
    private static void inAndOut(int loop, long snk) {
        while(loop-- > 0) {
            long capacity = snk;

            while(!device.isEmpty()) {
                capacity -= device.peek();
                if(capacity < 0) break;

                device.poll();
            }
        }
    }
}

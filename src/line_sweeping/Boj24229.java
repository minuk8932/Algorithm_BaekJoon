package line_sweeping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * 백준 24229번: 모두싸인 출근길
 *
 * @see https://www.acmicpc.net/problem/24229
 *
 */
public class Boj24229 {

    private static Queue<Bridge24229> pq;
    private static Queue<Bridge24229> rebuilding;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>(Comparator
                .comparingInt(Bridge24229::getFirst)
                .thenComparing(Bridge24229::getRevSecond));
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.offer(new Bridge24229(Integer.parseInt(st.nextToken()), 1));
            pq.offer(new Bridge24229(Integer.parseInt(st.nextToken()), -1));
        }

        merging();
        System.out.println(hopping());
    }

    /**
     *
     * Merging
     *
     * line 49 ~ 57: line sweeping
     *
     */
    private static void merging() {
        int L = -1;
        int section = 0;

        rebuilding = new PriorityQueue<>(Comparator
                .comparingInt(Bridge24229::getFirst)
                .thenComparing(Bridge24229::getSecond));

        while(!pq.isEmpty()) {
            Bridge24229 current = pq.poll();
            section -= current.getRevSecond();

            if(L == -1 && section == 1) L = current.getFirst();
            if(L != -1 && section == 0){
                rebuilding.offer(new Bridge24229(L, current.getFirst()));
                L = -1;
            }
        }
    }

    /**
     *
     * Hopping
     *
     * line 80 ~ 81: greedy
     *
     * @return
     */
    private static int hopping() {
        Bridge24229 start = rebuilding.poll();
        int L = start.getFirst();
        int R = start.getSecond();

        int max = 0;

        while(!rebuilding.isEmpty()) {
            Bridge24229 current = rebuilding.poll();

            int moved = R - L;
            max = Math.max(max, R + moved);
            if(current.getFirst() > max) break;

            L = current.getFirst();
            R = current.getSecond();
        }

        return R;
    }
}

class Bridge24229 {
    private int first;
    private int second;

    public Bridge24229(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public int getRevSecond() {
        return -second;
    }

    public int getSecond() {
        return second;
    }
}

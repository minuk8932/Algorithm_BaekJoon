package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 2513번: 통학 버스
 *
 * @see https://www.acmicpc.net/problem/2513/
 *
 */
public class Boj2513 {
    private static ArrayList<Pair> list = new ArrayList<>();

    private static class Pair implements Comparable <Pair>{
        int index;
        int count;

        public Pair(int index, int count) {
            this.index = index;
            this.count = count;
        }

        @Override
        public int compareTo(Pair p) {
            return this.index < p.index ? -1: 1;
        }
    }

    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int index = p - S;
            list.add(new Pair(index, c));           // save information
        }

        list.add(new Pair(0, 0));

        Collections.sort(list);
        System.out.println(shuttle(K));
    }

    private static int shuttle(int k) {
        int distance = 0;
        int len = list.size();

        int mid = -1;

        for(int i = 0; i < len; i++) {
            if(list.get(i).index == 0) {
                mid = i;
                break;
            }
        }

        for(int i = 0; i < mid;){                      // left
            int count = 0, j;

            for(j = i; j < mid; j++){
                count += list.get(j).count;

                if(count > k) {
                    list.get(j).count = count - k;
                    break;
                }
            }

            distance -= list.get(i).index * 2;
            i = j;
        }

        for(int i = len - 1; i > mid; i--){             // right
            int count = 0, j;

            for(j = i; j > mid; j--){
                count += list.get(j).count;

                if(count > k){
                    list.get(j).count = count - k;
                    j++;
                    break;
                }
            }

            distance += list.get(i).index * 2;
            i = j;
        }

        return distance;
    }
}

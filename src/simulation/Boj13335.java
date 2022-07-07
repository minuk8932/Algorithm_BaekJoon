package simulation;

import common.Pair;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 13335번: 트럭
 *
 * @see https://www.acmicpc.net/problem/13335
 *
 */
public class Boj13335 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(crossBridge(a, w, L));
    }

    private static int crossBridge(int[] a, int w, int l) {
        Queue<Pair<Integer>> bridge = new ArrayDeque<>();
        int time = 0;
        int weight = 0;

        int index = 0;
        while(index < a.length) {

            while(!bridge.isEmpty()){
                Pair<Integer> current = bridge.peek();
                if(time - current.getSecond() < w) break;

                weight -= current.getFirst();
                bridge.poll();
            }

            boolean flag = false;

            while(index < a.length && weight + a[index] <= l) {
                weight += a[index];
                bridge.offer(new Pair.Builder<>(a[index++], time).build());
                time++;

                flag = true;
            }

            if(!flag) time++;
        }

        return time + w;
    }
}

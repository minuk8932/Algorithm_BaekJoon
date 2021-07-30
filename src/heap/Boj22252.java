package heap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 22252번: 정보 상인 호석
 *
 * @see https://www.acmicpc.net/problem/22252
 *
 */
public class Boj22252 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Q = Integer.parseInt(br.readLine());

        Map<String, PriorityQueue<Long>> map = new HashMap<>();
        long info = 0;

        while(Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            if(cmd == 1) {
                int k = Integer.parseInt(st.nextToken());

                if(map.containsKey(name)) {
                    while(k-- > 0) {
                        map.get(name).offer(-Long.parseLong(st.nextToken()));
                    }
                }
                else {
                    PriorityQueue<Long> pq = new PriorityQueue<>();
                    while(k-- > 0) {
                        pq.offer(-Long.parseLong(st.nextToken()));
                    }

                    map.put(name, pq);
                }
            }
            else {
                if(!map.containsKey(name)) continue;
                int b = Integer.parseInt(st.nextToken());

                while(b-- > 0) {
                    if(map.get(name).isEmpty()) break;
                    info -= map.get(name).poll();
                }
            }
        }

        System.out.println(info);
    }
}

package heap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 *
 * @author exponential-e
 * 백준 7662번: 이중 우선순위 큐
 *
 * @see https://www.acmicpc.net/problem/7662/
 *
 */
public class Boj7662 {
    private static final String NEW_LINE = "\n";
    private static final String E = "EMPTY";
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0){
            int k = Integer.parseInt(br.readLine());
            TreeMap<Long, Integer> heap = new TreeMap<>();              // sorted HashMap

            while (k-- > 0){
                StringTokenizer st = new StringTokenizer(br.readLine());
                char cmd = st.nextToken().charAt(0);
                long val = Long.parseLong(st.nextToken());

                if (cmd == 'D'){
                    if(heap.isEmpty()) continue;
                    long key = 0;

                    if(val < 0){
                        key = heap.firstKey();

                        if(heap.get(key) == 1) heap.pollFirstEntry();
                        else heap.put(key, heap.get(key) - 1);
                    }

                    if(val > 0){
                        key = heap.lastKey();

                        if(heap.get(key) == 1) heap.pollLastEntry();
                        else heap.put(key, heap.get(key) - 1);
                    }
                }
                else{
                    if(!heap.containsKey(val)) heap.put(val, 1);
                    else heap.put(val, heap.get(val) + 1);
                }
            }

            if(heap.isEmpty()) sb.append(E).append(NEW_LINE);
            else sb.append(heap.lastKey()).append(SPACE).append(heap.firstKey()).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }
}

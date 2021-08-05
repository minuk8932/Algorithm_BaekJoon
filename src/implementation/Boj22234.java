package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 22234번: 가희와 은행
 *
 * @see https://www.acmicpc.net/problem/22234
 *
 */
public class Boj22234 {

    private static Map<Integer, Customer> entrance = new HashMap<>();
    private static final String NEW_LINE = "\n";

    private static class Customer {
        int person;
        int time;

        public Customer(int person, int time) {
            this.person = person;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        List<Customer> customers = new LinkedList<>();
        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            customers.add(new Customer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int M = Integer.parseInt(br.readLine());

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            entrance.put(c, new Customer(P, t));
        }

        System.out.println(bankProcess(T, W, customers));
    }

    /**
     *
     * Bank process
     *
     * line 83 ~ 85: spared work
     * line 87 ~ 89: get next
     *
     * @param t
     * @param w
     * @param custo
     * @return
     */
    private static String bankProcess(int t, int w, List<Customer> custo) {

        Customer current = custo.remove(0);

        int[] timeLine = new int[w + 1];
        int loop = Math.min(t, current.time);

        for(int i = 1; i <= w; i++) {
            timeLine[i] = current.person;
            loop--;

            if(entrance.containsKey(i)) custo.add(entrance.get(i));
            if(loop > 0) continue;

            int diff = current.time - t;
            if(diff > 0) {
                custo.add(new Customer(current.person, diff));
            }

            if(custo.isEmpty()) continue;
            current = custo.remove(0);
            loop = Math.min(current.time, t);
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= w; i++) {
            sb.append(timeLine[i]).append(NEW_LINE);
        }

        return sb.toString();
    }
}

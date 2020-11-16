package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20115번: 에너지 드링크
 *
 * @see https://www.acmicpc.net/problem/20115
 *
 */
public class Boj20115 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        while(N-- > 0) {
            pq.offer(-Integer.parseInt(st.nextToken()));
        }

        System.out.println(result(pq));
    }

    private static double result(PriorityQueue<Integer> pq) {
        double sum = -pq.poll();

        while(!pq.isEmpty()){
            sum += -pq.poll() / 2.0;
        }

        return sum;
    }
}

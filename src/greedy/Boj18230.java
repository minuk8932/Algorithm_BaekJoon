package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18230번: 2xN 에쁜 타일링
 *
 * @see https://www.acmicpc.net/problem/18230/
 *
 */
public class Boj18230 {
    private static PriorityQueue<Long> pqA = new PriorityQueue<>();
    private static PriorityQueue<Long> pqB = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        input(A, br.readLine(), pqA);
        input(B, br.readLine(), pqB);

        System.out.println(search(N));
    }

    private static void input(int n, String line, PriorityQueue<Long> pq) {
        StringTokenizer st = new StringTokenizer(line);

        for(int i = 0; i < n; i++){
            pq.offer(-Long.parseLong(st.nextToken()));
        }
    }

    private static long search(int n) {
        long max = 0;

        while(n > 0) {
            if(n % 2 == 1) {                            // odd size
                max -= pqA.poll();
                n--;
            }
            else {
                if(pqA.size() < 2) {
                    if(pqB.isEmpty()) continue;
                    max -= pqB.poll();
                    n -= 2;
                }
                else if(pqB.isEmpty()) {
                    if(pqA.size() < 2) continue;
                    max -= (pqA.poll() + pqA.poll());
                    n -= 2;
                }
                else {
                    long peek = pqA.poll();
                    long a = peek + pqA.peek();
                    long b = pqB.peek();

                    if (a < b) {                        // compare one B two A
                        pqA.poll();
                        max -= a;
                    } else {
                        pqA.offer(peek);
                        max -= pqB.poll();
                    }

                    n -= 2;
                }
            }
        }

        return max;
    }
}

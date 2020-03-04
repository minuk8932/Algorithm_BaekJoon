package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 12888번: 돌 그룹
 *
 * @see https://www.acmicpc.net/problem/12886/
 *
 */
public class Boj12886 {
    private static HashSet<Long> visit = new HashSet<>();
    private static final long[] CIPHER = {1_000L, 1_000_000L};

    private static class Pair{
        int x;
        int y;
        int z;

        public Pair(int x, int y, int z){
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public static Pair sort(Pair p){                // sort value
            int[] arr = {p.x, p.y, p.z};
            Arrays.sort(arr);
            return new Pair(arr[0], arr[1], arr[2]);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        System.out.println(bfs(A, B, C));
    }

    private static int bfs(int a, int b, int c){
        Queue<Pair> q = new LinkedList<>();
        q.offer(Pair.sort(new Pair(a, b, c)));

        while(!q.isEmpty()){
            Pair current = q.poll();

            Pair next = new Pair(0, 0, 0);

            if(current.x == current.y && current.y == current.z) return 1;      // grouping

            if(current.x != current.y){                     // min: x, max: y
                next.x = current.x + current.x;
                next.y = current.y - current.x;
                next.z = current.z;

                long val = make(next.x, next.y, next.z);    // used
                if(!visit.contains(val)) {
                    visit.add(val);
                    q.offer(Pair.sort(new Pair(next.x, next.y, next.z)));
                }
            }

            if(current.y != current.z){
                next.y = current.y + current.y;
                next.z = current.z - current.y;
                next.x = current.x;

                long val = make(next.x, next.y, next.z);
                if(!visit.contains(val)) {
                    visit.add(val);
                    q.offer(Pair.sort(new Pair(next.x, next.y, next.z)));
                }
            }

            if(current.x != current.z){
                next.x = current.x + current.x;
                next.z = current.z - current.x;
                next.y = current.y;

               long val = make(next.x, next.y, next.z);
                if(!visit.contains(val)) {
                    visit.add(val);
                    q.offer(Pair.sort(new Pair(next.x, next.y, next.z)));
                }
            }
        }

        return 0;
    }

    private static long make(int a, int b, int c){
        long val = a * CIPHER[1] + b * CIPHER[0] + c;
        return val;
    }
}

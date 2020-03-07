package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 12744번: 팬케이크 쌓기
 *
 * @see https://www.acmicpc.net/problem/12744/
 *
 */
public class Boj12744 {
    private static final String PLUS = "+", MINUS = "-";
    private static String result = "";

    private static int N;

    private static class Pair{
        int num;
        boolean flag;

        public Pair(int num, boolean flag){
            this.num = num;
            this.flag = flag;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        Pair[] pairs = new Pair[N];
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            pairs[i] = new Pair(Integer.parseInt(st.nextToken()), st.nextToken().equals(PLUS));
            sb.append(PLUS).append((i + 1));
        }

        result = sb.toString();
        System.out.println(bfs(pairs));
    }

    private static int bfs(Pair[] pairs){
        int count = 0;

        String start = makeVisit(pairs);
        if(start.equals(result)) return count;

        Queue<Pair[]> q = new LinkedList<>();
        HashSet<String> visit = new HashSet<>();

        q.offer(pairs);
        visit.add(start);

        while(true){
            int size = q.size();

            while(size-- > 0){
                Pair[] current = q.poll();

                boolean flag = makeVisit(current).equals(result);       // is result ?
                if(flag) return count;

                for(int i = 1; i <= N; i++){
                    Pair[] next = flipflop(current, i);
                    String nextVisit = makeVisit(next);

                    if(visit.contains(nextVisit)) continue;
                    visit.add(nextVisit);

                    q.offer(next);
                }
            }

            count++;
        }
    }

    private static String makeVisit(Pair[] arr){
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < N; i++){
            builder.append(arr[i].flag ? PLUS: MINUS).append(arr[i].num);
        }

        return builder.toString();
    }

    private static Pair[] flipflop(Pair[] p, int idx){
        Pair[] tmp = new Pair[N];
        int tidx = idx;

        for(int i = 0; i < idx; i++){                       // flipping over i
            tmp[--tidx] = new Pair(p[i].num, !p[i].flag);
        }

        for(int i = idx; i < N; i++){
            tmp[i] = new Pair(p[i].num, p[i].flag);
        }

        return tmp;
    }
}

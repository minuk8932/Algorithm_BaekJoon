package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 1043번: 거짓말
 *
 * @see https://www.acmicpc.net/problem/1043/
 *
 */
public class Boj1043 {
    private static ArrayList<Integer>[] part;
    private static ArrayList<Integer>[] query;
    private static Queue<Integer> q = new LinkedList<>();

    private static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int count = Integer.parseInt(st.nextToken());

        visit = new boolean[N];
        for(int i = 0; i < count; i++) {
            int person = Integer.parseInt(st.nextToken()) - 1;
            visit[person] = true;
            q.offer(person);
        }

        part = new ArrayList[N];
        query = new ArrayList[M];

        for(int i = 0; i < N; i++) {
            part[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            query[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            count = Integer.parseInt(st.nextToken()) - 1;

            try {
                int prev = Integer.parseInt(st.nextToken()) - 1;
                query[i].add(prev);

                while(count-- > 0){
                    int current = Integer.parseInt(st.nextToken()) - 1;
                    part[prev].add(current);
                    part[current].add(prev);

                    query[i].add(current);

                    prev = current;
                }
            } catch (NoSuchElementException nsee) {
                break;
            }
        }

        System.out.println(parties());
    }

    private static int parties() {
        while(!q.isEmpty()){
            int current = q.poll();

            for(int next: part[current]) {
                if(visit[next]) continue;
                visit[next] = true;

                q.offer(next);
            }
        }

        int count = 0;

        for(int i = 0; i < query.length; i++) {
            boolean flag = false;

            for(int participant: query[i]) {
                if(visit[participant]) flag = true;
            }

            if(flag) continue;
            count++;
        }

        return count;
    }
}

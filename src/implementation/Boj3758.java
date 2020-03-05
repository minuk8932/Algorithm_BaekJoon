package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 3758번: KCPC
 *
 * @see https://www.acmicpc.net/problem/3758/
 *
 */
public class Boj3758 {
    private static Solved[][] teams;
    private static final String NEW_LINE = "\n";

    private static class Solved implements Comparable<Solved>{
        int idx;
        int time;
        int report;
        int score;

        public Solved(int idx, int time, int report, int score){
            this.idx = idx;
            this.time = time;
            this.report = report;
            this.score = score;
        }

        @Override
        public int compareTo(Solved s) {
            if(this.score > s.score) return -1;
            else if(this.score < s.score) return 1;
            else {
                if(this.report < s.report) return -1;
                else if(this.report > s.report) return 1;
                else {
                    if(this.time < s.time) return -1;
                    else if(this.time > s.time) return 1;
                    else return 0;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());

            teams = new Solved[n][k];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < k; j++){
                    teams[i][j] = new Solved(0, 0, 0, 0);
                }
            }

            int time = 1;

            while(m-- > 0){
                st = new StringTokenizer(br.readLine());
                int i = Integer.parseInt(st.nextToken()) - 1;
                int j = Integer.parseInt(st.nextToken()) - 1;
                int s = Integer.parseInt(st.nextToken());

                teams[i][j].idx = i;
                teams[i][j].score = Math.max(teams[i][j].score, s);
                teams[i][j].time = time;

                teams[i][j].report++;
                time++;
            }

            PriorityQueue<Solved> pq = new PriorityQueue<>();
            for(int i = 0; i < n; i++){
                Solved s = new Solved(i, 0, 0, 0);

                for(int j = 0; j < k; j++){                         // totals
                    s.score += teams[i][j].score;
                    s.time = Math.max(s.time, teams[i][j].time);
                    s.report += teams[i][j].report;
                }

                pq.offer(s);
            }

            int count = 1;

            while(!pq.isEmpty()){                   // sort
                Solved current = pq.poll();
                if(current.idx == t) break;
                count++;
            }

            sb.append(count).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }
}

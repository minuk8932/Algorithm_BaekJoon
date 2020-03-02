import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj14464 {
    private static int[] t;
    private static int N, C;
    private static final int INF = 100_000;

    private static class Cow implements Comparable<Cow>{
        int from;
        int to;

        public Cow(int from, int to){
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Cow c) {
            if(this.from < c.from) return -1;
            else if (this.from > c.from) return 1;
            else {
                if(this.to - this.from < c.to - c.from) return -1;
                else if(this.to - this.from > c.to - c.from) return 1;
                else return 0;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        t = new int[C];
        for(int i = 0; i < C; i++){
            t[i] = Integer.parseInt(br.readLine());
        }

        Cow[] cows = new Cow[N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            cows[i] = new Cow(from, to);
        }

        System.out.println(finder(cows));
    }

    private static int finder(Cow[] cow){
        Arrays.sort(t);
        Arrays.sort(cow);

        int idx = 0, jdx = 0, count = 0;

        while(idx < C && jdx < N){
            System.out.print("jdx" + jdx + ": " + cow[jdx].from + " " + cow[jdx].to + " " + "idx" + idx + ": " + t[idx] + " ");

            if(cow[jdx].from <= t[idx] && t[idx] <= cow[jdx].to) {
                System.out.println(t[idx]);
                count++;
                idx++;
                jdx++;
            }
            else{
                int tmp = binarySearch(idx, C - 1, cow[jdx].from, cow[jdx].to);
                System.out.println();
                if(tmp == INF) jdx++;
                else idx = tmp;
            }
        }

        return count;
    }

    private static int binarySearch(int start, int end, int low, int high){
        int res = INF;

        while(start <= end){
            int mid = (start + end) / 2;

            if(t[mid] >= low){
                end = mid - 1;
                if(t[mid] <= high) res = Math.min(mid, res);
            }
            else{
                start = mid + 1;
            }
        }

        return res;
    }
}

package line_sweeping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17026번: Mountain View
 *
 * @see https://www.acmicpc.net/problem/17026/
 *
 */
public class Boj17026 {
    private static class Coordinate implements Comparable<Coordinate>{
        int x;
        int y;

        public Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Coordinate c) {
            if(this.x < c.x) return -1;
            else if(this.x > c.x) return 1;
            else{
                if(this.y > c.y) return -1;
                else if(this.y < c.y) return 1;
                else return 0;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Coordinate[] c = new Coordinate[N];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            c[i] = new Coordinate(x - y, x + y);
        }

        Arrays.sort(c);
        System.out.println(mountainView(N, c));
    }

    private static int mountainView(int n, Coordinate[] c){
        int count = 1;
        Coordinate prev = c[0];

        for(int i = 1; i < n; i++){
            if(prev.x <= c[i].x && prev.y >= c[i].y) continue;      // covered

            count++;
            prev = c[i];                // consider moutain hight
        }

        return count;
    }
}

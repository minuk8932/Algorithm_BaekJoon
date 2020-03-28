package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 2819번: 상근이의 로봇
 *
 * @see https://www.acmicpc.net/problem/2819/
 *
 */
public class Boj2819 {
    private static final HashMap<Character, Coordinate> PAIR = new HashMap<>();
    private static final String NEW_LINE = "\n";
    private static final char UP = 'S', DOWN = 'J', LEFT = 'Z', RIGHT = 'I';

    private static Coordinate start = new Coordinate(0, 0);
    private static long result;

    private static class Coordinate{
        int x;
        int y;

        public Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        init();

        Coordinate[][] coor = new Coordinate[2][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            result += Math.abs(x) + Math.abs(y);                            // prefix sum
            coor[0][i] = new Coordinate(x, y);
            coor[1][i] = new Coordinate(x, y);
        }

        Comparator<Coordinate> ySort = new Comparator<Coordinate>() {       // sort y
            @Override
            public int compare(Coordinate c1, Coordinate c2) {
                return c1.y - start.y < c2.y - start.y ? -1: 1;
            }
        };

        Arrays.sort(coor[0], ySort);

        Comparator<Coordinate> xSort = new Comparator<Coordinate>() {       // sort x
            @Override
            public int compare(Coordinate c1, Coordinate c2) {
                return c1.x - start.x < c2.x - start.x ? -1: 1;
            }
        };

        Arrays.sort(coor[1], xSort);

        char[] move = br.readLine().toCharArray();
        StringBuilder sb = new StringBuilder();

        for(char c: move){
            Coordinate current = PAIR.get(c);

            if(c == UP || c == DOWN){
                if(c == UP) transfer(N, coor[0], 0);
                else transfer(N, coor[0], 1);
            }
            else{
                if(c == RIGHT) transfer(N, coor[1], 2);
                else transfer(N, coor[1], 3);
            }

            start.x += current.x;                   // move
            start.y += current.y;
            sb.append(result).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void init(){
        PAIR.put(UP, new Coordinate(0, 1));
        PAIR.put(DOWN, new Coordinate(0, -1));
        PAIR.put(RIGHT, new Coordinate(1, 0));
        PAIR.put(LEFT, new Coordinate(-1, 0));
    }

    private static void transfer(int n, Coordinate[] arr, int flag){
        int left = 0, right = n - 1;
        long idx = flag == 0 || flag == 2 ? -1: n;

        while(left <= right){
            int mid = (left + right) / 2;
            int target = flag < 2 ? arr[mid].y: arr[mid].x;

            if(flag == 0){                              // y++
                if(target <= start.y){
                    left = mid + 1;
                    idx = mid;
                }
                else{
                    right = mid - 1;
                }
            }
            else if(flag == 1){                         // y--
                if(target >= start.y){
                    right = mid - 1;
                    idx = mid;
                }
                else{
                    left = mid + 1;
                }
            }
            else if(flag == 2){                         // x++
                if(target <= start.x){
                    left = mid + 1;
                    idx = mid;
                }
                else{
                    right = mid - 1;
                }
            }
            else {                                      // x--
                if(target >= start.x){
                    right = mid - 1;
                    idx = mid;
                }
                else{
                    left = mid + 1;
                }
            }
        }

        if(flag == 0 || flag == 2) {                    // calculate
            result += (idx + 1);
            result -= (n - idx - 1);
        }
        else{
            result -= idx;
            result += n - idx;
        }
    }
}

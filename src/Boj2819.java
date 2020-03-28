import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Boj2819 {
    private static final HashMap<Character, Coordinate> PAIR = new HashMap<>();
    private static int result;

    private static final String NEW_LINE = "\n";

    private static class Coordinate {
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

        Coordinate[] coor = new Coordinate[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            result += Math.abs(x) + Math.abs(y);
            coor[i] = new Coordinate(x, y);
        }

        char[] move = br.readLine().toCharArray();
        StringBuilder sb = new StringBuilder();

        for(char c: move){
            transfer(N, coor, c);
            sb.append(result).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void init(){
        PAIR.put('S', new Coordinate(0, 1));
        PAIR.put('J', new Coordinate(0, -1));
        PAIR.put('I', new Coordinate(1, 0));
        PAIR.put('Z', new Coordinate(-1, 0));
    }

    private static void transfer(int n, Coordinate[] coor, char c){
        Coordinate current = PAIR.get(c);
        int moved = (current.x + current.y) * n;

        result += moved;
    }
}

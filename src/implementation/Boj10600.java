package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 10600번: Web Colors
 *
 * @see https://www.acmicpc.net/problem/10600/
 *
 */
public class Boj10600 {
    private static final HashMap<Integer, String> idx = new HashMap<>();
    private static final String NEW_LINE = "\n";

    private static final Colors[] COLOR = {new Colors(255, 255, 255),
            new Colors(192, 192, 192),
            new Colors(128, 128, 128),
            new Colors(0, 0, 0),
            new Colors(255, 0, 0),
            new Colors(128, 0, 0),
            new Colors(255, 255, 0),
            new Colors(128, 128, 0),
            new Colors(0, 255, 0),
            new Colors(0, 128, 0),
            new Colors(0, 255, 255),
            new Colors(0, 128, 128),
            new Colors(0, 0, 255),
            new Colors(0, 0, 128),
            new Colors(255, 0, 255),
            new Colors(128, 0, 128)};

    private static class Colors{
        int r;
        int g;
        int b;

        public Colors(int r, int g, int b){
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        init();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            Colors rgb = new Colors(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            if(rgb.r * rgb.g * rgb.b == -1) break;
            sb.append(idx.get(getDifference(rgb))).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int getDifference(Colors rgb){
        int idx = 0;
        int min = Integer.MAX_VALUE;
        int res = -1;

        for(final Colors C: COLOR){
            Colors diff = new Colors(C.r - rgb.r, C.g - rgb.g, C.b - rgb.b);
            int pow = diff.r * diff.r + diff.g * diff.g + diff.b * diff.b;

            if(min > pow){
                min = pow;
                res = idx;
            }

            idx++;
        }

        return res;
    }

    private  static void init(){
        idx.put(0, "White");
        idx.put(1, "Silver");
        idx.put(2, "Gray");
        idx.put(3, "Black");
        idx.put(4, "Red");
        idx.put(5, "Maroon");
        idx.put(6, "Yellow");
        idx.put(7, "Olive");
        idx.put(8, "Lime");
        idx.put(9, "Green");
        idx.put(10, "Aqua");
        idx.put(11, "Teal");
        idx.put(12, "Blue");
        idx.put(13, "Navy");
        idx.put(14, "Fuchsia");
        idx.put(15, "Purple");
    }
}

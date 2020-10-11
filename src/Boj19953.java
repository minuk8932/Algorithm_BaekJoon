import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Boj19953 {
    private static ArrayList<Integer> period = new ArrayList<>();

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        getPeriod(v, m);
        Point result = coordinate(t);
        System.out.println(result.x + " " + result.y);
    }

    private static Point coordinate(int time) {
        int size = period.size();

        int gcd = getGcd(period.size(), 4);
        int lcm = size * 4 / gcd;

        Point p = new Point(0, 0);

        int loop = lcm;
        int add = 0;
        int index = 0;

        while(loop-- > 0) {
            if(add % 2 == 0) p.y += (add < 2 ? 1: -1) * period.get(index);
            else p.x += (add < 2 ? 1: -1) * period.get(index);
            index++;
            index %= size;
            add++;
            add %= 4;
        }

        int value = time / lcm;
        int mod = time % lcm;

        p.x *= value;
        p.y *= value;

        while(mod-- > 0) {
            if(add % 2 == 0) p.y += (add < 2 ? 1: -1) * period.get(index);
            else p.x += (add < 2 ? 1: -1) * period.get(index);
            index++;
            index %= size;
            add++;
            add %= 4;
        }

        return p;
    }

    private static void getPeriod(int v, int m) {
        HashSet<Integer> set = new HashSet<>();
        int start = v;

        while(!set.contains(start)) {
            set.add(start);
            period.add(start);
            start *= m;
            start %= 10;
        }
    }

    private static int getGcd(int a, int b) {
        if(b == 0) return a;
        return getGcd(b, a % b);
    }
}

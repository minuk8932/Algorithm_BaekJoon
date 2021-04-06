import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj1338 {

    private static final String NO = "Unknwon Number";
    private static Set<Long> visit = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        System.out.println(guess(from, to, x, y));
    }

    private static String guess(int from, int to, long x, int y) {
        int count = 0;
        long result = Long.MAX_VALUE;

        long target = y;
//        long time = 1L;
        long time = from / x - y;

        while(!outOfRange(x, from, to, target)) {
//            System.out.println(from + " " + to + " " + target + " " + time);
            if(target >= from && target <= to && !visit.contains(target)){
                visit.add(target);
                result = target;
                count++;
            }

            if(count >= 2) return NO;

            target = x * time + y;
            time++;
        }

        return result == Long.MAX_VALUE ? NO: result + "";
    }

    private static boolean outOfRange(long x, int from, int to, long target) {
        return (x < 0 && target < from) || (x >= 0 && target > to);
    }
}

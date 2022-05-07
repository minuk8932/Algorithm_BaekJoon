package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author exponential-e
 * 백준 3407번: 맹세
 *
 * @see https://www.acmicpc.net/problem/3407
 *
 */
public class Boj3407 {

    private static final String O = "YES\n";
    private static final String X = "NO\n";
    private static final Set<String> PERIODIC_TABLE = new HashSet<>();

    private static boolean[] visit;
    private static String word;

    public static void main(String[] args ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init();

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            word = br.readLine();
            int N = word.length();
            visit = new boolean[N + 2];

            sb.append(search()? O: X);
        }

        System.out.println(sb);
    }

    private static boolean search() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        visit[0] = true;

        while (!q.isEmpty()) {
            int current = q.poll();

            if (current == word.length()) return true;
            String a = "";
            String b = "";

            a = a + word.charAt(current);

            if(current < word.length() - 1) {
                b = b + word.charAt(current) + word.charAt(current + 1);
            }

            for(String period: PERIODIC_TABLE) {
                if (a.equals(period) && !visit[current + 1]) {
                    visit[current + 1] = true;
                    q.offer(current + 1);
                }

                if (b.equals(period) && !visit[current + 2]) {
                    visit[current + 2] = true;
                    q.offer(current + 2);
                }
            }
        }

        return false;
    }

    private static void init() {
        PERIODIC_TABLE.add("h");PERIODIC_TABLE.add("he");
        PERIODIC_TABLE.add("li");PERIODIC_TABLE.add("be");PERIODIC_TABLE.add("b");
        PERIODIC_TABLE.add("c");PERIODIC_TABLE.add("n");PERIODIC_TABLE.add("o");
        PERIODIC_TABLE.add("f");PERIODIC_TABLE.add("ne");
        PERIODIC_TABLE.add("na");PERIODIC_TABLE.add("mg");PERIODIC_TABLE.add("al");
        PERIODIC_TABLE.add("si");PERIODIC_TABLE.add("p");PERIODIC_TABLE.add("s");
        PERIODIC_TABLE.add("cl");PERIODIC_TABLE.add("ar");
        PERIODIC_TABLE.add("k");PERIODIC_TABLE.add("ca");PERIODIC_TABLE.add("sc");
        PERIODIC_TABLE.add("ti");PERIODIC_TABLE.add("v");PERIODIC_TABLE.add("cr");
        PERIODIC_TABLE.add("mn");PERIODIC_TABLE.add("fe");PERIODIC_TABLE.add("co");
        PERIODIC_TABLE.add("ni");PERIODIC_TABLE.add("cu");PERIODIC_TABLE.add("zn");
        PERIODIC_TABLE.add("ga");PERIODIC_TABLE.add("ge");PERIODIC_TABLE.add("as");
        PERIODIC_TABLE.add("se");PERIODIC_TABLE.add("br");PERIODIC_TABLE.add("kr");
        PERIODIC_TABLE.add("rb");PERIODIC_TABLE.add("sr");PERIODIC_TABLE.add("y");
        PERIODIC_TABLE.add("zr");PERIODIC_TABLE.add("nb");PERIODIC_TABLE.add("mo");
        PERIODIC_TABLE.add("tc");PERIODIC_TABLE.add("ru");PERIODIC_TABLE.add("rh");
        PERIODIC_TABLE.add("pd");PERIODIC_TABLE.add("ag");PERIODIC_TABLE.add("cd");
        PERIODIC_TABLE.add("in");PERIODIC_TABLE.add("sn");PERIODIC_TABLE.add("sb");
        PERIODIC_TABLE.add("te");PERIODIC_TABLE.add("i");PERIODIC_TABLE.add("xe");
        PERIODIC_TABLE.add("cs");PERIODIC_TABLE.add("ba");
        PERIODIC_TABLE.add("hf");PERIODIC_TABLE.add("ta");PERIODIC_TABLE.add("w");
        PERIODIC_TABLE.add("re");PERIODIC_TABLE.add("os");PERIODIC_TABLE.add("ir");
        PERIODIC_TABLE.add("pt");PERIODIC_TABLE.add("au");PERIODIC_TABLE.add("hg");
        PERIODIC_TABLE.add("tl");PERIODIC_TABLE.add("pb");PERIODIC_TABLE.add("bi");
        PERIODIC_TABLE.add("po");PERIODIC_TABLE.add("at");PERIODIC_TABLE.add("rn");
        PERIODIC_TABLE.add("fr");PERIODIC_TABLE.add("ra");
        PERIODIC_TABLE.add("rf");PERIODIC_TABLE.add("db");PERIODIC_TABLE.add("sg");
        PERIODIC_TABLE.add("bh");PERIODIC_TABLE.add("hs");PERIODIC_TABLE.add("mt");
        PERIODIC_TABLE.add("ds");PERIODIC_TABLE.add("rg");PERIODIC_TABLE.add("cn");
        PERIODIC_TABLE.add("fl");PERIODIC_TABLE.add("lv");
        PERIODIC_TABLE.add("la");PERIODIC_TABLE.add("ce");PERIODIC_TABLE.add("pr");
        PERIODIC_TABLE.add("nd");PERIODIC_TABLE.add("pm");PERIODIC_TABLE.add("sm");
        PERIODIC_TABLE.add("eu");PERIODIC_TABLE.add("gd");PERIODIC_TABLE.add("tb");
        PERIODIC_TABLE.add("dy");PERIODIC_TABLE.add("ho");PERIODIC_TABLE.add("er");
        PERIODIC_TABLE.add("tm");PERIODIC_TABLE.add("yb");PERIODIC_TABLE.add("lu");
        PERIODIC_TABLE.add("ac");PERIODIC_TABLE.add("th");PERIODIC_TABLE.add("pa");
        PERIODIC_TABLE.add("u");PERIODIC_TABLE.add("np");PERIODIC_TABLE.add("pu");
        PERIODIC_TABLE.add("am");PERIODIC_TABLE.add("cm");PERIODIC_TABLE.add("bk");
        PERIODIC_TABLE.add("cf");PERIODIC_TABLE.add("es");PERIODIC_TABLE.add("fm");
        PERIODIC_TABLE.add("md");PERIODIC_TABLE.add("no");PERIODIC_TABLE.add("lr");
    }
}

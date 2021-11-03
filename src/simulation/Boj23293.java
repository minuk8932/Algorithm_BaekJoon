package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Boj23293 {

    private static TreeSet<Integer> badRequestPlayer = new TreeSet<>();
    private static TreeSet<Integer> badRequestLog = new TreeSet<>();

    private static HashMap<Integer, Integer> position = new HashMap<>();
    private static int[][] item;

    private static final char MOVE = 'M';
    private static final char FARMING = 'F';
    private static final char CRAFT = 'C';
    private static final char ATTACK = 'A';

    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        init(N);

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int log = Integer.parseInt(st.nextToken());
            int player = Integer.parseInt(st.nextToken()) - 1;
            char code = st.nextToken().charAt(0);
            int factor = Integer.parseInt(st.nextToken()) - 1;

            switch (code) {
                case MOVE:
                    position.put(player, factor);
                    break;

                case FARMING:
                    positionCheck(log, player, factor);
                    break;

                case CRAFT:
                    int factor1 = Integer.parseInt(st.nextToken()) - 1;
                    craftingCheck(log, player, factor, factor1);
                    break;

                case ATTACK:
                    attackCheck(log, player, factor);
                    break;
            }
        }

        System.out.println(print());
    }

    private static String print() {
        StringBuilder sb = new StringBuilder();

        int size = badRequestLog.size();
        sb.append(size).append(NEW_LINE);

        while(!badRequestLog.isEmpty()) {
            sb.append(badRequestLog.pollFirst()).append(SPACE);
        }

        if(size != 0) sb.append(NEW_LINE);

        size = badRequestPlayer.size();
        sb.append(size).append(NEW_LINE);

        while(!badRequestPlayer.isEmpty()) {
            sb.append(badRequestPlayer.pollFirst() + 1).append(SPACE);
        }

        return sb.toString();
    }

    private static void attackCheck(int l, int one, int another) {
        if(position.get(one) == position.get(another)) return;
        badRequestPlayer.add(one);
        badRequestLog.add(l);
    }

    private static void positionCheck(int l, int p, int f) {
        item[f][p]++;
        if(position.get(p) != f) badRequestLog.add(l);
    }

    private static void craftingCheck(int l, int p, int f1, int f2) {
        if(item[f1][p] > 0 && item[f2][p] > 0) {
            item[f1][p]--;
            item[f2][p]--;

            return;
        }

        badRequestLog.add(l);

        if(item[f1][p] > 0) item[f1][p]--;
        if(item[f2][p] > 0) item[f2][p]--;
    }

    private static void init(int n) {
        item = new int[53][n];

        for(int i = 0; i < n; i++) {
            position.put(i, 0);
        }
    }
}

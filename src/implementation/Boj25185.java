package implementation;

import common.Card;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25185번: 카드 뽑기
 *
 * @see https://www.acmicpc.net/problem/25185
 *
 */
public class Boj25185 {

    private static final String REST = ":)\n";
    private static final String NOT_REST = ":(\n";

    private static ArrayList<Card<Integer, Character>> cards;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            cards = new ArrayList<>();
            while(st.hasMoreTokens()) {
                String input = st.nextToken();
                cards.add(new Card.Builder(input.charAt(0) - '0', input.charAt(1)).build());
            }

            sb.append(restable());
        }

        System.out.println(sb);
    }

    private static String restable() {
        Collections.sort(cards, Comparator.comparing(Card<Integer, Character>::getWord)
            .thenComparingInt(Card::getNumber));

        int[] adds = {0, 1, 2, 4};
        int[] arr = {0, 1, 2};
        for(int add: adds) {
            int loop = 2;

            for(int i = loop; i >= 0; i--) {
                arr[i] += add % 2;
                add /= 2;
            }

            if(continuous(arr[0], arr[1], arr[2])) return REST;
        }

        if(allEquals(0) || allEquals(1)) return REST;
        if(pairs(0, 1, 2, 3)
            || pairs(0, 2, 1, 3)
            || pairs(0, 3, 2, 1)) return REST;

        return NOT_REST;
    }

    private static boolean pairs(int a, int b, int c, int d) {

        return cards.get(a).getWord() == cards.get(b).getWord() &&
            cards.get(a).getNumber() == cards.get(b).getNumber() &&
            cards.get(c).getWord() == cards.get(d).getWord() &&
            cards.get(c).getNumber() == cards.get(d).getNumber();
    }

    private static boolean allEquals(int idx) {
        boolean flag = true;

        for(int i = 0; i < 2; i++) {
            flag &= cards.get(idx + i).getNumber() == cards.get(idx + 1 + i).getNumber();
            flag &= cards.get(idx + i).getWord() == cards.get(idx + 1 + i).getWord();
        }

        return flag;
    }

    private static boolean continuous(int a, int b, int c) {
        if(cards.get(a).getWord() != cards.get(b).getWord()
            || cards.get(b).getWord() != cards.get(c).getWord()) return false;

        int[] diff = {cards.get(a).getNumber() - cards.get(b).getNumber()
            , cards.get(b).getNumber() - cards.get(c).getNumber()};
        if(diff[0] == 0 || diff[1] == 0) return false;

        return diff[0] == diff[1] && diff[0] == -1;
    }
}

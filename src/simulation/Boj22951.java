package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 22951번: 송이의 카드 게임
 *
 * @see https://www.acmicpc.net/problem/22951
 *
 */
public class Boj22951 {

    private static final String SPACE = " ";

    private static class Card {
        int number;
        int person;

        public Card(int number, int person) {
            this.number = number;
            this.person = person;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = PARSE.apply(st.nextToken());
        int K = PARSE.apply(st.nextToken());

        Card[] cards = ARR.apply(N * K);
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < K; j++) {
                cards[i * K + j] = new Card(PARSE.apply(st.nextToken()), i + 1);
            }
        }

        System.out.println(rotating(cards));
    }

    private static String rotating(Card[] card) {
        Deque<Card> deq = new ArrayDeque<>();

        for(Card c: card) {
            deq.offerLast(c);
        }

        int size = deq.size();
        Card current = deq.poll();

        while(size-- > 1) {
            int loop = current.number;

            while(true) {
                current = deq.poll();
                if(--loop == 0) break;

                deq.offerLast(current);
            }
        }

        return current.person + SPACE + current.number;
    }

    private static final Function<String, Integer> PARSE = Integer::parseInt;
    private static final Function<Integer, Card[]> ARR = Card[]::new;

}

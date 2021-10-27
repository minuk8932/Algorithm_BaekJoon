package sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 20920번: 영단어 암기는 괴로워
 *
 * @see https://www.acmicpc.net/problem/20920
 *
 */
public class Boj20920 {

    private static Queue<Word> pq;
    private static Map<String, Integer> words;

    private static final String NEW_LINE = "\n";

    private static class Word {
        private String str;
        private int frequency;

        public Word(String str, int frequency) {
            this.str = str;
            this.frequency = frequency;
        }

        public int getLength() {
            return -this.str.length();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        words = new HashMap<>();

        while(N-- > 0) {
            String input = br.readLine();
            if(input.length() < M) continue;

            words.merge(input, 1, Integer::sum);
        }

        prioritySetting(words);
        System.out.println(printer());
    }

    private static String printer() {
        StringBuilder sb = new StringBuilder();

        while(!pq.isEmpty()) {
            sb.append(pq.poll().str).append(NEW_LINE);
        }

        return sb.toString();
    }

    private static void prioritySetting(Map<String, Integer> map) {
        pq = new PriorityQueue<>(Comparator.<Word>comparingInt(w -> -w.frequency)
                .thenComparingInt(Word::getLength)
                .thenComparing(w -> w.str));

        for(Map.Entry<String, Integer> word: map.entrySet()) {
            pq.offer(new Word(word.getKey(), word.getValue()));
        }
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj2621 {

    private static Map<Character, Integer> colorMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        coloring();

        Card[] cards = new Card[5];
        for(int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cards[i] = new Card(st.nextToken().charAt(0), Integer.parseInt(st.nextToken()));
        }

        /**
         *
         *  2. 4숫자 동일 -> 동일 + 800
         *  3. 3숫자 / 2숫자 각 동일 -> 3장 같 * 10 + 2장 같 + 700
         *
         *  6. 3 숫자 동일 -> 같은 숫자 + 400
         *  7. 2장씩 숫자 동일 -> 큰숫자 * 10 + 작은숫자 + 300
         *  8. 2장 숫자 동일 -> 같은 숫자 + 200
         *  9. 가장 큰 숫자 + 100
         */

        System.out.println(collecting(cards));
    }

    private static int collecting(Card[] cards) {
        int sum = 0;

        Arrays.sort(cards, Comparator
                .comparingInt(Card::getNumber)
                .thenComparing(Card::getColor)
        );

        int[] colors = new int[4];
        int[] numbers = new int[10];

        for(Card c: cards) {
            char color = c.getColor();
            int number = c.getNumber();

            colors[colorMap.get(color)]++;
            numbers[number]++;
        }

        for(int i = 0; i < 4; i++) {
            if(colors[i] != 5) continue;
            return (isConsecutive(numbers) ? 900: 600) + cards[4].getNumber();
        }

        if(isConsecutive(numbers)) return cards[4].getNumber() + 500;

        Map<Integer, Integer> tree = new TreeMap<>();
        for(int i = 0; i < 10; i++) {
            tree.put(-numbers[i], i);
        }



        return sum;
    }

    private static boolean isConsecutive(int[] nums) {
        int count = 0;

        for(int i = 0; i < nums.length; i++) {
            if(count == 5) return true;

            if(nums[i] != 0) count++;
            else count = 0;
        }

        return false;
    }

    private static void coloring() {
        colorMap.put('R', 0);
        colorMap.put('B', 1);
        colorMap.put('Y', 2);
        colorMap.put('G', 3);
    }
}

class Card {
    private char color;
    private int number;

    public Card(char color, int number) {
        this.color = color;
        this.number = number;
    }

    public char getColor() {
        return this.color;
    }

    public int getNumber() {
        return this.number;
    }
}

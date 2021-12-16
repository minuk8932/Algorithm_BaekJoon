package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 2621번: 카드 게임
 *
 * @see https://www.acmicpc.net/problem/2621
 *
 */
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

        System.out.println(collecting(cards));
    }

    private static int collecting(Card[] cards) {
        Arrays.sort(cards, Comparator.comparingInt(Card::getNumber));

        int[] colors = new int[4];
        int[] numbers = new int[10];

        for(Card c: cards) {
            char color = c.getColor();
            int number = c.getNumber();

            colors[colorMap.get(color)]++;
            numbers[number]++;
        }

        boolean allSame = false;
        int result = 0;

        for(int i = 0; i < 4; i++) {
            if(colors[i] != 5) continue;
            allSame = true;

            if(isConsecutive(numbers)) result = Math.max(900 + cards[4].getNumber(), result);
            break;
        }

        int[][] count = countings(numbers);

        if(count[0][4] != 0) result = Math.max(count[0][4] + 800, result);
        if(count[0][3] != 0) {
            if(count[0][2] != 0) result = Math.max(count[0][3] * 10 + count[0][2] + 700, result);
            else result = Math.max(count[0][3] + 400, result);
        }

        if(allSame) result = Math.max(cards[4].getNumber() + 600, result);
        if(isConsecutive(numbers)) result = Math.max(cards[4].getNumber() + 500, result);

        if (count[0][2] != 0) {
            if(count[1][2] != 0) result = Math.max(Math.max(count[1][2], count[0][2]) * 10 + Math.min(count[1][2], count[0][2]) + 300, result);
            else result = Math.max(count[0][2] + 200, result);
        }

        return Math.max(cards[4].getNumber() + 100, result);
    }

    private static int[][] countings(int[] nums) {
        int[][] check = new int[2][5];

        for (int val = 1; val < nums.length; val++) {
            if(check[0][nums[val]] != 0) check[1][nums[val]] = val;
            else check[0][nums[val]] = val;
        }

        return check;
    }

    private static boolean isConsecutive(int[] nums) {
        int count = 0;

        for(int i = 1; i < nums.length; i++) {
            if (count == 5) return true;

            if(nums[i] != 0) count++;
            else count = 0;
        }

        return count == 5;
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

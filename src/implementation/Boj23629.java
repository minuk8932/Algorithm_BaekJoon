package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 23629번: 이 얼마나 끔찍하고 무시무시한 수식이니
 *
 * @see https://www.acmicpc.net/problem/23629
 *
 */
public class Boj23629 {

    private static final String WA = "Madness!";
    private static final String SPACE = " ";
    private static final String EQ = "=\n";

    private static final char ADD = '+';
    private static final char SUB = '-';
    private static final char MUL = 'x';
    private static final char DIV = '/';
    private static final char EQU = '=';

    private static Set<Character> signs = new HashSet<>();
    private static Map<Long, String> longToString = new HashMap<>();
    private static Map<String, Long> stringToLong = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] fomula = br.readLine().toCharArray();
        init();

        System.out.println(getResult(fomula));
    }

    private static String getResult(char[] fn) {
        StringBuilder form = new StringBuilder();
        StringBuilder valueBuilder = new StringBuilder();

        List<Character> sign = new ArrayList<>();

        for(char f: fn) {
            if(f >= 'A' && f <= 'Z') {
                if(stringToLong.containsKey(valueBuilder.toString())) {
                    form.append(stringToLong.get(valueBuilder.toString()));
                    valueBuilder = new StringBuilder();
                }

                valueBuilder.append(f);
                continue;
            }

            int len = valueBuilder.length();
            if(len < 3 || len > 5) return WA;
            if(!stringToLong.containsKey(valueBuilder.toString())) return WA;

            form.append(stringToLong.get(valueBuilder.toString())).append(SPACE);
            valueBuilder = new StringBuilder();
            sign.add(f);

            if(!signs.contains(f)) return WA;
        }

        StringBuilder answer = new StringBuilder();
        StringTokenizer st = new StringTokenizer(form.toString());
        answer.append(st.nextToken());

        int idx = 0;

        while(st.hasMoreTokens()) {
            answer.append(sign.get(idx++)).append(st.nextToken());
        }
        answer.append(EQ);

        long result = getResult(form, sign);
        if(result < 0) answer.append(SUB);

        result = Math.abs(result);
        Deque<String> stack = new ArrayDeque<>();

        if(result == 0) stack.push(longToString.get(0L));
        else {
            while (result > 0) {
                stack.push(longToString.get(result % 10L));
                result /= 10;
            }
        }
        
        while(!stack.isEmpty()) {
            answer.append(stack.pop());
        }

        return answer.toString();
    }

    private static long getResult(StringBuilder builder, List<Character> sign) {
        StringTokenizer st = new StringTokenizer(builder.toString());
        long total = Long.parseLong(st.nextToken());

        int idx = 0;

        while(st.hasMoreTokens()) {
            long next = Long.parseLong(st.nextToken());

            char s = sign.get(idx++);
            switch (s) {
                case ADD:
                    total += next;
                    break;

                case SUB:
                    total -= next;
                    break;

                case MUL:
                    total *= next;
                    break;

                case DIV:
                    total /= next;
                    break;
            }
        }

        return total;
    }

    private static void init() {
        longToString.put(1L, "ONE"); longToString.put(2L, "TWO"); longToString.put(3L, "THREE");
        longToString.put(4L, "FOUR"); longToString.put(5L, "FIVE"); longToString.put(6L, "SIX");
        longToString.put(7L, "SEVEN"); longToString.put(8L, "EIGHT"); longToString.put(9L, "NINE"); longToString.put(0L, "ZERO");

        stringToLong.put("ONE", 1L); stringToLong.put("TWO", 2L); stringToLong.put("THREE", 3L);
        stringToLong.put("FOUR", 4L); stringToLong.put("FIVE", 5L); stringToLong.put("SIX", 6L);
        stringToLong.put("SEVEN", 7L); stringToLong.put("EIGHT", 8L); stringToLong.put("NINE", 9L); stringToLong.put("ZERO", 0L);

        signs.add(ADD);
        signs.add(SUB);
        signs.add(MUL);
        signs.add(DIV);
        signs.add(EQU);
    }
}

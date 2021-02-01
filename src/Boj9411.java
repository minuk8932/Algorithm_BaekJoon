import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Boj9411 {

    private static final String TERMINATE = "0";
    private static final String NEW_LINE = "\n";

    private static final char NEGATIVE = '-';
    private static final char DIVIDER = '.';
    private static final char EMPTY = ' ';

    private static boolean[] positive;
    private static char[][] value;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            ArrayList<String> inputs = new ArrayList<>();

            while(true) {
                String input = br.readLine();
                if(input.equals(TERMINATE)) break;

                inputs.add(input);
            }

            valueSpecify(inputs);
            sb.append(formula()).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static String formula() {
        StringBuilder number = new StringBuilder();
        int[] result = new int[61];

        for(int i = 0; i < result.length; i++) {
            if(value[0][i] == EMPTY || i == 30) continue;
            result[i] = value[0][i] - '0';
        }

        int carry = 0;
        int x = positive[0] ? 1: -1;

        for(int i = 1; i < value.length; i++) {
            int t = positive[i] ? 1: -1;
            int last = 0;

            for(int j = result.length - 1; j >= 0; j--) {
                if(value[i][j] == DIVIDER) continue;

                int a = result[j] * x;
                int b = (value[i][j] == EMPTY ? 0: value[i][j] - '0') * t;

                int add = a + b + carry;
                last = add;

                if(add < 0) {
                    carry = -1;
                    add += 10;
                }
                else {
                    carry = add > 9 ? 1: 0;
                }

                result[j] = add % 10;
            }

            x = last < 0 ? -1: 1;
        }

        if(carry == -1) {
            int c = 0;

            for(int i = result.length - 1; i > 30; i--) {
                if(result[i] == 0){
                    c = 0;
                    continue;
                }

                result[i] = 10 - result[i] + c;
                c = -1;
            }

            int s = 0;
            for(int i = 0; i < 30; i++) {
                s = i;
                if(result[i] != 9) break;
            }

            for(int i = 29; i >= s; i--) {
                if(result[i] == 9) break;
                result[i] = 10 - result[i] + c;
            }

            for(int i = s - 1; i >= 0; i--) {
                result[i] = 0;
            }

            number.append(NEGATIVE);
        }

        int start = 0, end = 60;
        for(int i = 0; i < 30; i++) {
            start = i;
            if(result[i] != 0) break;
        }

        for(int i = 60; i > 30; i--) {
            end = i;
            if(result[i] != 0) break;
        }

        if(end == 31 && result[end] == 0) end = 29;
        for(int i = start; i <= end; i++) {
            if(i == 30) number.append(DIVIDER);
            else number.append(result[i]);
        }

        return number.toString();
    }

    private static void valueSpecify(ArrayList<String> inputs){
        int size = inputs.size();
        int idx = 0;
        positive = new boolean[size];
        value = new char[size][61];

        for(int i = 0; i < size; i++) {
            Arrays.fill(value[i], EMPTY);
        }

        for(String in: inputs) {
            if(in.charAt(0) != NEGATIVE) positive[idx] = true;
            int len = in.length();

            int dot = 0;
            for(char c: in.toCharArray()) {
                if(c == DIVIDER) break;
                dot++;
            }

            value[idx][30] = DIVIDER;

            int d = 1;
            for(int i = dot - 1; positive[idx] ? i >= 0: i > 0; i--) {
                value[idx][30 - d] = in.charAt(i);
                d++;
            }

            d = 1;
            for(int i = dot + 1; i < len; i++) {
                value[idx][30 + d] = in.charAt(i);
                d++;
            }

            idx++;
        }
    }
}

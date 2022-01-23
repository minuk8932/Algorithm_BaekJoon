package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.BiPredicate;

/**
 *
 * @author exponential-e
 * 백준 21980번: 비슷한 번호판
 *
 * @see https://www.acmicpc.net/problem/21980
 *
 */
public class Boj21980 {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            st.nextToken();

            CarNumber21980[] numbers = new CarNumber21980[n];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                numbers[i] = new CarNumber21980(st.nextToken());
            }

            sb.append(finder(numbers)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static long finder(CarNumber21980[] numbers) {
        long answer = 0;
        long count = 1;

        CarNumber21980 src = numbers[0];
        for(int i = 1; i < numbers.length; i++) {
            if(IS_DIFFERENT.test(src, numbers[i])){
                answer += (count * (count - 1)) >> 1L;
                count = 1;

                src = numbers[i];
                continue;
            }

            count++;
        }

        return answer + ((count * (count - 1)) >> 1);
    }

    private static final BiPredicate<CarNumber21980, CarNumber21980> IS_DIFFERENT = (cn1, cn2) ->
            !cn1.isEqual(cn2.getNumber()) || cn1.getUppers() != cn2.getUppers();
}

class CarNumber21980 {
    private String number;
    private int uppers;

    public CarNumber21980(String number) {
        this.number = getSortedNumber(number);
        this.uppers = upperCount(number);
    }

    public String getNumber() {
        return number;
    }

    public int getUppers() {
        return uppers;
    }

    public boolean isEqual(String another) {
        int[] src = stringToIntArray(this.number.toLowerCase().toCharArray());
        int[] snk = stringToIntArray(another.toLowerCase().toCharArray());

        for(int i = 0; i < 26; i++) {
            if(src[i] != snk[i]) return false;
        }

        return true;
    }

    private int[] stringToIntArray(char[] inputs) {
        int[] alpha = new int[26];

        for(int i = 0; i < inputs.length; i++) {
            alpha[inputs[i] - 'a']++;
        }

        return alpha;
    }

    private String getSortedNumber(String input) {
        char[] word = input.toCharArray();
        StringBuilder builder = new StringBuilder();

        Arrays.sort(word);
        for(char w: word) {
            builder.append(w);
        }

        return builder.toString();
    }

    private int upperCount(String input) {
        char[] word = input.toCharArray();

        int count = 0;
        for(char w: word) {
            if(w < 'A' || w > 'Z') continue;
            count++;
        }

        return count;
    }
}
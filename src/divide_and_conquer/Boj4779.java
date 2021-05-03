package divide_and_conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author exponential-e
 * 백준 4779번: 칸토어 집합
 *
 * @see https://www.acmicpc.net/problem/4779
 *
 */
public class Boj4779 {

    private static final String NEW_LINE = "\n";
    private static final char HYPHEN = '-';
    private static final char SPACE = ' ';

    private static char[] cantor;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        StringBuilder sb = new StringBuilder();
        while((input = br.readLine()) != null) {
            int N = (int) Math.pow(3, Integer.parseInt(input));

            cantor = new char[N];
            Arrays.fill(cantor, SPACE);

            recursion(0, N - 1, N);
            sb.append(print(cantor)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static String print(char[] cantor) {
        StringBuilder sb = new StringBuilder();

        for(char c: cantor) {
            sb.append(c);
        }

        return sb.toString();
    }

    private static void recursion(int start, int end, int current) {
        if(current == 1){
            cantor[start] = HYPHEN;
            return;
        }

        int next = current / 3;
        recursion(start,start + next - 1, next);
        recursion(end - next + 1, end, next);
    }
}

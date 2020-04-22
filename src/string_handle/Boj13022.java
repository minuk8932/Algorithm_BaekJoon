package string_handle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 13022번: 늑대와 올바른 단어
 *
 * @see https://www.acmicpc.net/problem/13022/
 *
 */
public class Boj13022 {
    private static final char[] WORDS = {'w', 'o', 'l','f'};
    private static final String CUT = "1";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(judgement(br.readLine()));
    }

    private static int judgement (String input) {
        boolean flag = false;

        StringBuilder builder = new StringBuilder();

        for(char c: input.toCharArray()) {
            if(!flag && c == WORDS[0]) {                // cut by w
                flag = true;

                builder.append(1);
                builder.append(c);

                continue;
            }

            if(c != WORDS[0]) {
                builder.append(c);
                flag = false;
            }
            else{
                builder.append(c);
            }
        }

        StringTokenizer token = new StringTokenizer(builder.toString(), CUT);

        while(token.hasMoreTokens()) {
            String word = token.nextToken();

            int index = 0;
            char target = WORDS[index];

            int[] count = new int[4];

            for(char c: word.toCharArray()) {               // right sequence
                if(target == c){
                    count[index]++;
                    continue;
                }

                int next = (index + 1) % 4;
                if(WORDS[next] != c) return 0;

                index = next;
                target = WORDS[index];
                count[index]++;
            }

            for(int i = 1; i < 4; i++) {                    // right count
                if (count[i - 1] != count[i]) return 0;
            }
        }

        return 1;
    }
}

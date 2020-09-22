package string_handle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 19844번: 단어 개수 세기
 *
 * @see https://www.acmicpc.net/problem/19844
 *
 */
public class Boj19844 {
    private static final char[] VOWEL = {'a', 'e', 'o', 'u', 'i', 'h'};
    private static final String[] OMISSION = {"c", "j", "n", "m", "t", "s", "l", "d", "qu"};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(countWords(br.readLine()));
    }

    private static int countWords(String sentence) {
        StringTokenizer st = new StringTokenizer(sentence);
        LinkedList<String> datas = new LinkedList<>();

        while(st.hasMoreTokens()) {                 // split by " "
            datas.add(st.nextToken());
        }

        int size = datas.size();
        int total = 0;

        while(size-- > 0) {                         // split by "-"
            st = new StringTokenizer(datas.remove(), "-");

            while(st.hasMoreTokens()) {
                String current = st.nextToken();
                if(!current.contains("\'")) {       // if not has "'"
                    total++;
                    continue;
                }

                datas.add(current);
            }
        }

        while(!datas.isEmpty()) {
            String current = datas.remove();
            st = new StringTokenizer(current, "\'");

            int count = 1;

            String a = st.nextToken();
            String b = st.nextToken();

            for(final String O: OMISSION) {         // count omitted
                if(count != 1) break;
                if(!a.equals(O)) continue;

                for(final char V: VOWEL) {
                    if(V != b.charAt(0)) continue;
                    count++;
                    break;
                }
            }

            total += count;
        }

        return total;
    }
}

package sogang;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 서강 I번: 문자열 압축 해제
 *
 * @see https://www.acmicpc.net/contest/problem/725/9
 *
 */
public class I {

    private static Map<Character, String> matching = new HashMap<>();
    private static List<Character> result = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        while(N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String lower = st.nextToken();
            char upper = st.nextToken().charAt(0);

            matching.put(upper, lower);
        }
        
        converter(br.readLine().toCharArray());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken()) - 1;
        int E = Integer.parseInt(st.nextToken());

        System.out.println(printer(S, E));
    }

    private static void converter(char[] code) {
        for(char c: code) {
            String represent = matching.get(c);

            for(char rep: represent.toCharArray()) {
                result.add(rep);
            }
        }
    }

    private static String printer(int s, int e) {
        StringBuilder sb = new StringBuilder();

        for(int i = s; i < e; i++) {
            sb.append(result.get(i));
        }

        return sb.toString();
    }
}

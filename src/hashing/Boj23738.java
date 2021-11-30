package hashing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author exponential-e
 * 백준 23739번: Ресторан
 *
 * @see https://www.acmicpc.net/contest/problem/23738
 *
 */
public class Boj23738 {

    private static Map<Character, String> mapper = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init();

        System.out.println(printer(br.readLine().toCharArray()));
    }

    private static String printer(char[] russian) {
        StringBuilder sb = new StringBuilder();

        for(char ru: russian) {
            if (ru == 'A') sb.append('a');
            else if (ru == 'K') sb.append('k');
            else sb.append(mapper.get(ru));
        }

        return sb.toString();
    }

    private static void init() {
        mapper.put('B', "v");
        mapper.put('E', "ye");
        mapper.put('H', "n");
        mapper.put('P', "r");
        mapper.put('Y', "u");
        mapper.put('X', "h");
        mapper.put('C', "s");

        mapper.put('А', "a");
        mapper.put('К', "k");
        mapper.put('M', "m");
        mapper.put('O', "o");
        mapper.put('T', "t");
    }
}

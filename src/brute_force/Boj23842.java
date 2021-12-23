package brute_force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author exponential-e
 * 백준 23842번: 성냥개비
 *
 * @see https://www.acmicpc.net/problem/23842
 *
 */
public class Boj23842 {

    private static Map<Integer, Integer> matchMapper = new HashMap<>();

    private static final char PLUS = '+';
    private static final char EQUAL = '=';

    private static final String NONE = "impossible";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()) - 4;

        init();
        System.out.println(formulaBuilder(N));
    }

    private static String formulaBuilder(int n) {
        StringBuilder sb = new StringBuilder();

        for(int sum = 99; sum >= 0; sum--) {
            for(int left = 0; left <= 99; left++) {
                for(int right = 99; right >= 0; right--) {
                    if(sum != left + right) continue;

                    int count = n;

                    count -= valueToMatch(sum);
                    count -= valueToMatch(left);
                    count -= valueToMatch(right);

                    if(count != 0) continue;
                    return sb.append(String.format("%02d", left)).append(PLUS)
                            .append(String.format("%02d", right)).append(EQUAL)
                            .append(String.format("%02d", sum)).toString();
                }
            }
        }

        return NONE;
    }

    private static int valueToMatch(int value) {
        int except = 0;

        if(value == 0) return 12;
        if(value < 10) except += 6;

        while(value > 0) {
            except += matchMapper.get(value % 10);
            value /= 10;
        }

        return except;
    }

    private static void init() {
        matchMapper.put(1, 2); matchMapper.put(2, 5); matchMapper.put(3, 5);
        matchMapper.put(4, 4); matchMapper.put(5, 5); matchMapper.put(6, 6);
        matchMapper.put(7, 3); matchMapper.put(8, 7); matchMapper.put(9, 6); matchMapper.put(0, 6);
    }
}

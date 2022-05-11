package string_handle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author exponential-e
 * 백준 11478번: 서로 다른 부분 문자열의 개수
 *
 * @see https://www.acmicpc.net/problem/11478
 *
 */
public class Boj11478 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(substring(br.readLine().toCharArray()));
    }

    private static int substring(char[] s) {
        Set<String> partition = new HashSet<>();
        for(int i = 0; i < s.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(s[i]);
            partition.add(sb.toString());

            for(int j = i + 1; j < s.length; j++) {
                sb.append(s[j]);
                partition.add(sb.toString());
            }
        }

        return partition.size();
    }
}

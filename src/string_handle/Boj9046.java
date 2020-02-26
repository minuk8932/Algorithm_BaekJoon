package string_handle;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj9046 {
    private static final String NEW_LINE = "\n";
    private static final char MANY = '?';
    private static final char SPACE = ' ';

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(N-- > 0){
            char[] input = br.readLine().toCharArray();
            sb.append(finder(input)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static char finder(char[] word){
        int[] alpha = new int[26];
        int max = 0;

        for(char c: word){
            if(c == SPACE) continue;

            int idx = c - 97;
            alpha[idx]++;

            if(alpha[idx] > max) max = alpha[idx];
        }

        boolean flag = false;
        char result = SPACE;

        for(int i = 0; i < alpha.length; i++){
            if(max == alpha[i]){
                if(flag) return MANY;
                flag = true;

                result = (char) (i + 97);
            }
        }

        return result;
    }
}

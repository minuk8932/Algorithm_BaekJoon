package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 6550번: 부분 문자열
 *
 * @see https://www.acmicpc.net/problem/6550/
 *
 */
public class Boj6550 {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = "";

        while((input = br.readLine()) != null){
            StringTokenizer st = new StringTokenizer(input);
            char[] s = st.nextToken().toCharArray();
            char[] t = st.nextToken().toCharArray();

            sb.append(compare(s, t) ? "Yes": "No").append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static boolean compare(char[] a, char[] b){
        if(a.length > b.length) return false;

        int idx = 0;

        for(int i = 0; i < b.length; i++){
            if(idx == a.length) break;
            if(a[idx] == b[i]) idx++;           // find sub
        }

        return idx == a.length;
    }
}

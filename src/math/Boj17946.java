package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj17946 {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(N-- > 0){
            br.readLine();
            sb.append(1).append(NEW_LINE);          // divide: (k^2 + k + 2) / 2, to give: (k^2 + k) / 2
        }

        System.out.println(sb.toString());
    }
}

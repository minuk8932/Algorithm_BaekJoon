package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj18856 {
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(feedback(N));
    }

    private static String feedback(int n){
        StringBuilder sb = new StringBuilder();
        sb.append(n).append(NEW_LINE);
        sb.append(1).append(SPACE).append(2).append(SPACE);

        for(int i = 2; i < n - 1; i++){
            sb.append(i * 2).append(SPACE);
        }

        sb.append(101);

        return sb.toString();
    }
}

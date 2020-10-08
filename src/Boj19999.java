import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj19999 {
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        sb.append(2000 * N).append(SPACE).append(2000 * N);

        System.out.println(sb.toString());
    }
}

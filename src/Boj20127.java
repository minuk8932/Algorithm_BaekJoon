import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj20127 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] sequence = new int[N];
        for(int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(y(sequence));
    }

    private static int y(int[] seq) {
        int k = 0;

        

        return k;
    }
}

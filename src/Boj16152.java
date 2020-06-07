import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj16152 {
    private static int level = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        init(N);

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
        }
    }

    private static void init(int n) {
        if(n == 1) return;

        for(int i = 1; i < 31; i++) {
            int prev = 1 << (i - 1);
            int post = 1 << i;
            if(prev > n || n >= post) continue;

            level = i;
            return;
        }
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj19594 {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            int[] H = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                H[i] = Integer.parseInt(st.nextToken());
            }

            int[] D = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                D[i] = Integer.parseInt(st.nextToken());
            }

            sb.append(latency(H, D)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int latency(int[] h, int[] d) {
        int[] result = new int[h.length];
        Arrays.sort(d);

        int c = 0, idx = 0;
        int max = 0, alter = 0;

        for(int i = 0; i < h.length; i++) {
            int current = Math.max(0, c + h[i] - d[i]);
            result[i] = current;

            if(current > max) {
                max = current;
                alter = 1 + c - d[i];
                idx = i;
            }

            c += h[i];
        }

        int res = alter;
        for(int i = 0; i < result.length; i++) {
            if(i == idx) {
                res = Math.max(alter, res);
            }
            else {
                res = Math.max(result[i], res);
            }
        }

        return res;
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Boj16287 {

    private static final int INF = 1_000_001;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(pairing(a, w) ? "YES": "NO");
    }

    private static boolean pairing(int[] a, int weight) {
        int[] pairs = new int[weight + 1];
        Arrays.fill(pairs, INF);

        for(int mindex = 0; mindex < a.length; mindex++) {
            for(int maxindex = mindex + 1; maxindex < a.length; maxindex++) {
                int sum = a[mindex] + a[maxindex];

                int difference = weight - sum;
                if (difference <= 0) continue;

                if(pairs[difference] < mindex) return true;
                pairs[sum] = Math.min(pairs[sum], maxindex);
            }
        }

        return false;
    }

}

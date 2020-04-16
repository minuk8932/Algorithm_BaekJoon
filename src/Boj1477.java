import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1477 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] rest = new int[N];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            rest[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(rest);
        getInterval(rest, L);

        System.out.println(binarySearch(M));
    }

    private static void getInterval(int[] r, int l) {

    }

    private static int binarySearch(int m) {

        return 0;
    }
}

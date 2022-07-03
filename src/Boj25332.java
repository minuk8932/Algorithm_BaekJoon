import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj25332 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] A = input(br.readLine(), N);
        int[] B = input(br.readLine(), N);

        System.out.println( );
    }

    private static int[] input(String readLine, int n) {
        int[] arr = new int[n + 1];

        StringTokenizer st = new StringTokenizer(readLine);
        for(int i = 1; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) + arr[i - 1];
        }

        return arr;
    }
}

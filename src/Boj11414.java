import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11414 {
    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        System.out.println(find(Math.abs(A - B), A, B));
        System.out.println(System.currentTimeMillis() - start);
    }

    private static int find(int diff, int a, int b){
        if(diff == 0) return 1;
        int result = 1;
        int min = Math.min(a, b);

        while(true){
            int sum = min + result;

            if(sum % diff == 0) break;
            if(diff % sum == 0) break;

            result++;
        }

        return result;
    }
}

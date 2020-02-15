import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11414 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        System.out.println(find(Math.abs(A - B), B));
    }

    private static int find(int diff, int b){
        if(diff == 0) return 1;

        int loop = (int) Math.sqrt(diff) + 1;
        int result = 1;

        for(int i = 1; i < loop; i++){
            if(diff % i == 0){
                int[] tmp = {i, diff / i};

                int max = Math.max(Math.abs(tmp[0] - b), Math.abs(tmp[1] - b));
                if(result < max) result = max;
            }
        }

        return result;
    }
}

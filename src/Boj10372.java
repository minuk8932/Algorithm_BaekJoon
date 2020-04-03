import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj10372 {
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    private static int[] fibo = new int[46];
    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        init();

        StringBuilder sb = new StringBuilder();

        while(T-- > 0){
            int N = Integer.parseInt(br.readLine());
            int[] result = gabonacci(N);

            sb.append(result[0]).append(SPACE).append(result[1]).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void init(){
        fibo[0] = 0;
        fibo[1] = 1;

        int idx = 2;

        while(fibo[idx - 1] < INF && fibo[idx - 2] < INF){
            int sum = fibo[idx - 1] + fibo[idx - 2];
            if(sum < 0) break;

            fibo[idx] = sum;
            idx++;
        }

        for(int i = 0; i < fibo.length; i++){
            System.out.println(fibo[i]);
        }
    }

    private static int[] gabonacci(int n){
        int[] a = {n / 2, n / 2};
        int div = n / 8;

        int[] from = {a[0] - div, a[0] + div - 5};
        int[] to = {a[0] - div + 5, a[0] + div};

        int[] nfibo = new int[2];
        int max = 0;
        boolean flag = false;

        for(int i = from[0]; i <= to[0]; i++){
            for(int j = from[1]; j <= to[1]; j++){
                if(i + j == n && max < j - i) {
                    max = j - i;
                    nfibo[0] = i;
                    nfibo[1] = j;
//                    flag = true;
//                    break;
                }
            }

            if(flag) break;
        }

        while(true){
            int tmp = nfibo[1] - nfibo[0];
            if(tmp <= 0) break;

            nfibo[1] = nfibo[0];
            nfibo[0] = tmp;
        }

        return nfibo;
    }
}

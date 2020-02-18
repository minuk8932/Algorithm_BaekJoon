import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj6615 {
    private static final String[] FORMAT = {" needs ", " steps, ", "they meet at ", "\n"};
    private static int[] save = new int[3_000_001];

    public  static  void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if(A + B == 0) break;
            sb.append(A).append(FORMAT[0]).append(0).append(FORMAT[1])
                    .append(B).append(FORMAT[0]).append(1).append(FORMAT[1]).append(FORMAT[2]).append(3).append(FORMAT[3]);
        }

        System.out.println(sb.toString());
    }
}

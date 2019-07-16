import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

 

public class Boj12893{
    public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());

    	int[][] relate = new int[N][N];

    	while(M-- > 0){
    		relate[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;   		
    	}
    }

}
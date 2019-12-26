package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * 
 * 	@author exponential-e
 *	백준 2547번: 사탕 선생 고창영
 *
 *	@see https://www.acmicpc.net/problem/2547/
 *
 */
public class Boj2547{
    private static final String NEW_LINE = "\n";
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        while(T-- > 0){
            br.readLine();
            int N = Integer.parseInt(br.readLine());
            BigDecimal bd = new BigDecimal(0);
            
            for(int i = 0; i < N; i++){
                bd = bd.add(BigDecimal.valueOf(Long.parseLong(br.readLine())));
            }
            
            sb.append(bd.remainder(BigDecimal.valueOf(N)).equals(BigDecimal.ZERO) ? "YES": "NO").append(NEW_LINE);
        }
        
        System.out.println(sb.toString());
    }
}
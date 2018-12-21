package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5361번: 전투 드로이드 가격
 *
 *	@see https://www.acmicpc.net/problem/5361/
 *
 */
public class Boj5361{
    private static final char NEW_LINE = '\n';
    private static long[] cost = {35034, 23090, 19055, 12530, 18090};
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        while(T-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int loop = st.countTokens();
            long total = 0;
            
            for(int i = 0; i < loop; i++){
            	total += (Integer.parseInt(st.nextToken())) * cost[i];
            }
            
            String res = getResult(total);
            
            sb.append('$').append(res.equals("0") ? "0.00" : res).append(NEW_LINE);
        }
        
        System.out.println(sb);		// 결과 출력
    }
    
    private static String getResult(long num) {
    	String word = String.valueOf(num);
        int leng = word.length();
        
        String res = "";
        for(int i = 0; i < leng; i++) {
        	if(i == leng - 2) res += '.';
        	res += word.charAt(i);
        }
        
        return res;
    }
}
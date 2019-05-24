package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 17253번: 삼삼한 수 2
 *	
 *	@see https://www.acmicpc.net/problem/17253/
 *
 */
public class Boj17253 {
    public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	long N = Long.parseLong(br.readLine());
    	
    	System.out.println(isSamSam(N) ? "YES": "NO");
    }
    
    private static boolean isSamSam(long n) {		// n = 3^a + 3^b + 3^c... 으로 정의할 수 있음
    	if(n == 0) return false;
    	
    	while(n > 0) {
    		if(n % 3 == 2) return false;			// 같은 지수승을 가진 3의 제곱수는 들어오지 않기 때문에 나머지로 2가 나올 수 없음
    		if(n % 3 == 1) n--;						// 어느 한개의 3의 제곱수가 완전히 나누어진 경우 (3^0)
    		
    		n /= 3;
    	}
    	
    	return true;
    }
}

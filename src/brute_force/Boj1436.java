package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1436번: 영화 감독 숌
 *
 *	@see https://www.acmicpc.net/problem/1436/
 *
 */
public class Boj1436 {
	private static final int INF = 10_000_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		System.out.println(search(N));
	}
	
	private static String search(int n) {
		String str = "666";
		if(n == 1) return str;
		
		int count = 1;
		
    	for(int i = 667; i < INF; i++) {
    		str = Integer.toString(i);
    		int leng = str.length();
    		
    		for(int j = 0; j < leng - 2; j++) {			// 666이 연속한 숫자만 걸러내기
    			if(str.charAt(j) == '6' && str.charAt(j + 1) == '6' && str.charAt(j + 2) == '6') {
    				count++;
    				break;
    			}
    		}
    		
    		if(count  == n) return str;
    	}
		
		return null;				// 최댓값 찾아보기
	}
}

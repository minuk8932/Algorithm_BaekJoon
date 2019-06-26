package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1975번: Number Game
 *
 *	@see https://www.acmicpc.net/problem/1975/
 *
 */
public class Boj1975 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			sb.append(getZeroCount(N)).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static int getZeroCount(int n) {
		int count = 0;
		
		for(int i = 2; i < n + 1; i++) {
			int loop = n;
			
			while(loop % i == 0) {			// 가장 초기 나누어 떨어지는 약수의 갯수
				count++;
				loop /= i;
			}
		}
		
		return count;
	}
}

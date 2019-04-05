package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 13909번: 창문 닫기
 *
 *	@see https://www.acmicpc.net/problem/13909/
 *
 */
public class Boj13909 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double N = Double.parseDouble(br.readLine());
		
		System.out.println(process(N));			// 약수가 홀수개인 경우만 1 (즉, 어떤 수의 제곱수)
	}
	
	private static int process(double n) {
		int loop = (int) Math.sqrt(n);
		int count = 0;
		
		for(int i = 1; i < loop + 1; i++) {		// 제곱근까지만 반복문을 통해 제곱수의 갯수 찾기
			if(i * i <= n) count++;
		}
		
		return count;
	}
	
}

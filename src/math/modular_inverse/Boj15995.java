package math.modular_inverse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15995번: 잉여 역수 구하기
 *
 *	@see https://www.acmicpc.net/problem/15995/
 *
 */
public class Boj15995 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		System.out.println(fermatTheorem(a, m));
	}
	
	private static int fermatTheorem(int a, int mod) {
		for(int result = 0; result < mod; result++) {
			if(((a % mod) * (result % mod)) % mod == 1) return result;		// 잉여 역수 반환
		}
		
		return 0;
	}
}

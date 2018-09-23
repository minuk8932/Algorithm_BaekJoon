package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 9625번: BABBA
 *
 *	@see https://www.acmicpc.net/problem/9625/
 *
 */
public class Boj9625 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Alpha[] abt = new Alpha[46];
		
		abt[0] = new Alpha(1, 0);
		abt[1] = new Alpha(0, 1);
		
		for(int i = 2; i < abt.length; i++) {			// 피보나치 함수 구현
			abt[i] = new Alpha(abt[i - 2].a + abt[i - 1].a, abt[i - 2].b + abt[i - 1].b);
		}
		
		System.out.println(abt[N].a + " " + abt[N].b);		// a와 b의 결과 갯수를 출력
	}
	
	/**
	 * 알파벳 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Alpha{
		int a;
		int b;
		
		public Alpha(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
}

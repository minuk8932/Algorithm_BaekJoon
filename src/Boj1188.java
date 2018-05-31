import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1188번: 음식평론가
 *
 *	@see https://www.acmicpc.net/problem/1188/
 *
 */
public class Boj1188 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int N = Integer.parseInt(st.nextToken()); 	// 소세지 수
		int M = Integer.parseInt(st.nextToken());	// 평론가의 수
		
		System.out.println(M - gcd(N, M));
	}
	
	/**
	 * 유클리드 호제법
	 * @param num1: 소세지 수
	 * @param num2: 평론가의 수
	 * @return	최종적으로 최대 공약수 반환
	 */
	private static int gcd(int num1, int num2) {
		if(num2 == 0) {
			return num1;
		}
		return gcd(num2, num1 % num2);
	}
}

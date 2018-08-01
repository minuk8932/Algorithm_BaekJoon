package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1075번: 나누기
 *
 *	@see https://www.acmicpc.net/problem/1075/
 *
 */
public class Boj1075 {
	private static final int CIPHER = 100;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int F = Integer.parseInt(br.readLine());

		N = N / CIPHER * CIPHER;			// 맨 끝 두자리를 구하기 위해 N의 값 연산
		while(N % F != 0) N++;				// F의 배수를 구함 (최소 배수)
		
		System.out.println((N %= CIPHER) < 10 ? "0" + N : N);	// 최종 나머지값이 10보다 작으면 앞에 0을 붙여서 출력
	}
}

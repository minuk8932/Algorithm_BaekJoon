package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 8393번: 합
 *
 *	@see https://www.acmicpc.net/problem/8393/
 *
 */
public class Boj8393 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println((N + 1) * N / 2);				// 합 공식
	}
}

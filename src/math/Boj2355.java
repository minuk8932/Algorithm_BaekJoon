package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2355번: 시그마
 *
 *	@see https://www.acmicpc.net/problem/2355/
 *
 */
public class Boj2355 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		if(A > B) {			// A가 더 큰 경우 값을 바꿔줌
			long tmp = B;
			B = A;
			A = tmp;
		}
		
		System.out.println((B - A + 1) * (B + A) / 2);		// 시그마 공식을 이용한 결과값 출력
	}	
}

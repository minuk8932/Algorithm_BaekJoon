package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 15894번: 수학은 체육과목 입니다.
 *
 *	@see https://www.acmicpc.net/problem/15894/
 *	
 */
public class Boj15894 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = (Long.parseLong(br.readLine()) * 2 * 2);		// 위아래 변의 총 길이와 양쪽의 변의 길이를 합한 값
		
		System.out.println(n);		// 결과 값 출력
	}
}

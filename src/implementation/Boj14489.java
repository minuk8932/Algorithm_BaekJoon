package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14489번: 치킨 두 마리(..)
 *
 *	@see https://www.acmicpc.net/problem/14489/
 *
 */
public class Boj14489 {
	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());	// 욱제의 통장 잔고
		int C = Integer.parseInt(br.readLine()) * 2;					// 치킨 한마리 가격
		
		System.out.println(A >= C ? A - C : A);	// 조건에 따라 결과 값 출력
	}
}

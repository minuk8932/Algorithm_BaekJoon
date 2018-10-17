package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 4999번: 아!
 *
 *	@see https://www.acmicpc.net/problem/4999/
 *
 */
public class Boj4999 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(br.readLine().length() >= br.readLine().length() ? "go" : "no");		// 결과 값 출력
	}
}

package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 15857번: 백준 온라인 저
 *
 *	@see https://www.acmicpc.net/problem/15857/
 *
 */
public class Boj15857{
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 문제 번호 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] ans = {'a', 'b', 'b', 'c', 'd', 'd', 'd', 'c'};	// 문제에 해당하는 답을 문자 배열에 담은 후
		System.out.println(ans[N - 1]);					// 해당 인덱스에 맞게 N - 1을 통해 정답을 출력
	}
}

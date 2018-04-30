package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2420번: 사파리 월드
 *
 *	@see https://www.acmicpc.net/problem/2420/
 *
 */
public class Boj2420 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Long N = Long.parseLong(st.nextToken());	// 최대 출력 가능 값의 범위가 크므로 Long으로 받는다
		Long M = Long.parseLong(st.nextToken());
		
		Long sub = N - M;
		System.out.println(sub > 0 ? sub : -sub);		// 절댓값 처리 후 출력
	}
}

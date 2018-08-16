package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1193번: 분수찾기
 *
 *	@see https://www.acmicpc.net/problem/1193/
 *
 */
public class Boj1193 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		
		int boundary = 0;
		int loop = 1;
		
		while(X > 0) {
			boundary = X;	// X가 0보다 큰 값일 때의 마지막 값 (몇 항의 몇번째인가)
			X -= loop;
			loop++;			// 몇 항에 존재하는가
		}
		
		StringBuilder sb = new StringBuilder();
		if(loop % 2 == 0) sb.append(Math.abs(loop - boundary)).append("/").append(boundary);	// 짝수인 경우 정방향
		else sb.append(boundary).append("/").append(Math.abs(loop - boundary));					// 홀수인 경우 역방향
		
		System.out.println(sb.toString());			// 결과 값 출력
	}
}

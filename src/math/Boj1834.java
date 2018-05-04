package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1834번: 나머지와 몫이 같은 수
 *
 *	@see https://www.acmicpc.net/problem/1834/
 *
 */
public class Boj1834 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		long res = 0;
		
		for(long i = 1; i < N; i++){
			res += (i * N + i);			// 나머지와 몫이 같은 수는 N보다 작은 수에서만 존재하므로, 해당값을 구해서 결과 변수에 더해줌
		}
		
		System.out.println(res);		// 결과 값 출력
	}
}
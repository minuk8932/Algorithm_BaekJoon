package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 14470번: 전자레인지
 *
 *	@see https://www.acmicpc.net/problem/14470/
 *
 */
public class Boj14470 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int A = Integer.parseInt(br.readLine());
		int B = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());
		int D = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		
		int res = 0;
		boolean checker = false;
		
		while(true) {
			if(A < 0) {						// 0도 미만 온도의 음식인 경우
				res += Math.abs(A) * C;
				A = 0;
			}
			
			else if(A == 0) {			// 0도에서 해동해야하는 경우
				res += D;
				
				checker = true;
				A = 1;
			}
			
			else {						// 0도보다 큰 온도의 음식인 경우
				int sec = 0;
				
				if(checker) sec = B;
				else sec = B - A;
				
				res += sec * E;
				A = B;
			}
			
			if(A == B) break;			// 목적온도에 도달한 경우
		}
		
		System.out.println(res);		// 결과 시간을 출력
	}
}

package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1668번: 트로피 진열
 *
 *	@see https://www.acmicpc.net/problem/1668/
 *
 */
public class Boj1668 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] trophy = new int[N];
		for(int i = 0; i < N; i++) {
			trophy[i] = Integer.parseInt(br.readLine());
		}
		
		int fwd = 1, max = trophy[0];
		for(int i = 1; i < N; i++) {			// 정방향
			if(max < trophy[i]) {				// 이제까지 최대 높이의 트로피보다 더 높은 트로피가 나타난 경우
				max = Math.max(trophy[i], max);
				fwd++;
			}
		}
		
		int bwd = 1;
		max = trophy[N - 1];
		for(int i = N - 2; i >= 0; i--) {		// 역방향
			if(max < trophy[i]) {				// 이제까지 최대 높이의 트로피보다 더 높은 트로피가 나타난 경우
				max = Math.max(trophy[i], max);
				bwd++;
			}
		}
		
		System.out.println(fwd + " " + bwd);		// 결과 값 출력
	}
}

package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 2217번: 로프
 *
 *	@see https://www.acmicpc.net/problem/2217/
 *
 */
public class Boj2217 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] rope = new int[N];
		for(int i = 0; i < N; i++) {
			rope[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(rope);		// 오름 차순 정렬
		
		int max = 0;
		for(int i = N - 1; i >= 0; i--) {	// 제일 긴 줄부터 차례로 순번에 따라 곱해가며 갑을 구해보고
			int tmp = rope[i] * (N - i);
			
			if(tmp > max) {			// 그 중 최댓값을 찾아냄
				max = tmp;
			}
		}
		
		System.out.println(max);	// 최댓값 출력
	}
}

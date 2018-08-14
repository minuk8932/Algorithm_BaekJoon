package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 2790번: F7
 *
 *	@see https://www.acmicpc.nnet/problem/2790/
 *
 */
public class Boj2790 {
	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int res = 0;
		int score = 0;
		
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {					// 드라이버들이 얻은 점수 입력
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		for(int i = N - 1; i >= 0; i--) {					// 오름차순 역순으로 반복문 진행
			if(arr[i] + N >= score) res++;					// 우승 후보인 경우
			score = Math.max(score, arr[i] + (N - 1) - i + 1);	// 한계 점수를 구해줌
		}
		
		System.out.println(res);	// 우승 가능한 인원 수 출력
	}
}

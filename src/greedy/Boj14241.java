package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14241번: 슬라임 합치기
 *
 *	@see https://www.acmicpc.net/problem/14241/
 *
 */
public class Boj14241 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] slime = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			slime[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(slime);
		
		long score = 0;
		for(int i = N - 1; i > 0; i--) {
			score += slime[i] * slime[i - 1];	// 큰 수부터 점수를 더하고
			slime[i - 1] += slime[i];			// 슬라임을 합해줌
		}
		
		System.out.println(score);		// 결과 점수 출력
	}
}

package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2506번: 점수 계산
 *
 *	@see https://www.acmicpc.net/problem/2506/
 *
 */
public class Boj2506 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int score = 1, res = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if(num == 1) res += score++;		// 점수 획득
			else score = 1;						// 점수 획득 불가 및 가산점 초기화
		}
		
		System.out.println(res);		// 결과 값 출력
	}
}

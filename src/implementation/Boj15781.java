package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15781번: 헬멧과 조끼
 *
 *	@see https://www.acmicpc.net/problem/15781/
 *
 */
public class Boj15781 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int h = 0, v = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			
			if(tmp > h) {		// 헬멧 방어력 최댓값
				h = tmp;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			
			if(tmp > v) {		// 조끼 방어력 최댓값
				v = tmp;
			}
		}
		
		System.out.println(v + h);		// 방어력 최댓값 출력
	}
}

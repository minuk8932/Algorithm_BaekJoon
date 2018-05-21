package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 *	@author minchoba
 *	백준 10103번: 주사위 게임
 *
 *	@see https://www.acmicpc.net/problem/10103
 *
 */
public class Boj10103 {
	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		int N = Integer.parseInt(br.readLine());
		int A = 100, B =100;			// 초기값 설정
		
		StringBuilder sb = new StringBuilder();		
		for(int i = 0; i < N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int scoreA = Integer.parseInt(st.nextToken());
			int scoreB = Integer.parseInt(st.nextToken());
			
			if(scoreA > scoreB){			// 조건에 따른 점수 감점
				B -= scoreA;
			}
			else if(scoreB > scoreA){
				A -= scoreB;
			}
		}
		sb.append(A).append("\n").append(B);	// 최종 점수를 버퍼에 담고
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}

}

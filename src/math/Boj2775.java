package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2775번: 부녀회장이 될테야
 *
 *	@see https://www.acmicpc.net/problem/2775/
 *
 */
public class Boj2775 {
	private static int[][] resident = new int[15][15];
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		num();		// 14호까지 인원수 구하기
		
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			int k =Integer.parseInt(br.readLine());
			int n = Integer.parseInt(br.readLine());
			
			sb.append(resident[k][n - 1]).append(NEW_LINE);		// 해당 호수의 인원을 버퍼에 담고
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
	
	/**
	 * 인원수 합 메소드
	 * 
	 */
	private static void num() {
		for(int i = 0; i < 15; i++) {		// 첫 호는 무조건 1명
			resident[i][0] = 1;
			resident[0][i] = i + 1;			// 1층의 인원은 하나씩 채워줌
		}
		
		for(int i = 1; i < 15; i++) {
			for(int j = 1; j < 15; j++) {
				resident[i][j] = resident[i][j - 1] + resident[i - 1][j];		// 규칙에 따라 값을 호수 층에 저장해줌
			}
		}
	}
}

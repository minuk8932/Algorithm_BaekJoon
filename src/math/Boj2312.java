package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2312번: 수 복원하기
 *
 *	@see https://www.acmicpc.net/problem/2312/
 *
 */
public class Boj2312 {
	private static final int INF = 100_001;
	private static final char SPACE = ' ';
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			int[] primeFac = new int[INF];				// 소인수의 갯수를 셀 배열
			int num = Integer.parseInt(br.readLine());
			
			for(int i = 2; num > 1; i++) {				
				while(true) {
					if(num % i != 0) break;		// 나머지가 0이 아니면 반복문 종료하고 다음 소인수로 넘어감
					
					primeFac[i]++;		// 해당 i가 소인수면 배열의 갯수를 늘려주고, num을 i로 나눔
					num /= i;
				}
			}
			
			for(int i = 2; i < primeFac.length; i++) {
				if(primeFac[i] == 0) continue;
				
				sb.append(i).append(SPACE).append(primeFac[i]).append(NEW_LINE); 	// 소인수와 해당 수의 갯수를 버퍼에 저장
			}
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
}

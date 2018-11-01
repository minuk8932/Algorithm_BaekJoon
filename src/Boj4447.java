import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 4447번: 좋은놈 나쁜놈
 *
 *	@see https://www.acmicpc.net/problem/4447/
 *
 */
public class Boj4447 {
	private static final String IS = " is ";
	private static final String B = "A BADDY";
	private static final String G = "GOOD";
	private static final String N = "NEUTRAL";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		
		while(n-- > 0) {
			int bCnt = 0, gCnt = 0;
			
			for(char w : br.readLine().toCharArray()) {
				if(w == 'b' || w == 'B') bCnt++;
				if(w == 'g' || w == 'G') gCnt++;
				
				sb.append(w);
			}
			
			// cnt에 따라 알맞는 값을 버퍼에 저장
			sb.append(IS).append(bCnt == gCnt ? N : bCnt > gCnt ? B : G).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());	// 결과 값 한번에 출력
	}
}

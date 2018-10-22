package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2443번: 별 찍기 6
 *
 *	@see https://www.acmicpc.net/problem/2443/
 *
 */
public class Boj2443 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final String STAR = "*";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		int size = 2 * (N + 1) - 1;
		
		for(int i = N - 1; i >= 0; i--) {
			int loop = (i + 1) * 2 - 1;
			
			for(int j = 0; j < size; j++) {
				if(j == N - (i + 1)) {		// 별이 찍히기 시작할 위치
					while(loop-- > 0) {		// 찍힐 별의 갯수
						sb.append(STAR);
					}
					
					break;					// 별이 다 찍혔으므로 다음 별로 넘어감
				}
				else {
					sb.append(SPACE);		// 공백찍기
				}
			}
			
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
}

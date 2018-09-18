package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2083번: 럭비 클럽
 *
 *	@see https://www.acmicpc.net/problem/2083/
 *
 */
public class Boj2083 {
	private static final String S = " Senior\n";
	private static final String J = " Junior\n";
	private static final String TERMINATE = "# 0 0";
	
	private static final int LIMIT_AGE = 17;
	private static final int LIMIT_WEIGHT = 80;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String line = br.readLine();
			
			if(TERMINATE.equals(line)) break;				// 마지막 줄인 경우 반복문 종료
			StringTokenizer st = new StringTokenizer(line);
			
			sb.append(st.nextToken())		// 이름과 나이 몸무게를 체크 후 Senior Junior를 구분해 버퍼에 담아줌
			.append(Integer.parseInt(st.nextToken()) > LIMIT_AGE || Integer.parseInt(st.nextToken()) >= LIMIT_WEIGHT ? S : J);
		}
		
		System.out.println(sb.toString());	// 결과 값 한번에 출력
	}
}

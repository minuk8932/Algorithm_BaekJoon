package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 4880번: 다음수
 *
 *	@see https://www.acmicpc.net/problem/4880/
 *
 */
public class Boj4880 {
	private static final String PLUS = "AP";
	private static final String TIMES = "GP";
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final String TERMINATE = "0 0 0";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String line = br.readLine();
			
			if(line.equals(TERMINATE)) break;				// 종료에 해당하는 입력이 들어온 경우
			
			StringTokenizer st = new StringTokenizer(line);
			int a1 = Integer.parseInt(st.nextToken());
			int a2 = Integer.parseInt(st.nextToken());
			int a3 = Integer.parseInt(st.nextToken());
			
			boolean isAp = (a1 - a2 == a2 - a3) ? true : false;		// 등차인지 확인
			int next = isAp ? a3 + (a2 - a1) : a3 * (a2 / a1);		// 각 경우에 따라 다음 수를 구함
			
			sb.append(isAp ? PLUS : TIMES).append(SPACE).append(next).append(NEW_LINE);		// 다음 수와 등차 등비 여부를 버퍼에 담음
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
}

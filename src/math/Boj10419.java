package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10419번: 지각
 *
 *	@see https://www.acmicpc.net/problem/10419/
 *
 */
public class Boj10419 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int d = Integer.parseInt(br.readLine());
			sb.append(lecTimer(d)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int lecTimer(int time) {
		int limit = 0;
		
		for(int i = 0; i < time; i++) {
			int total = (i + 1) * i;
			
			if(total > time) break;
			limit = i;				//지각 가능 시간 저장
		}
		
		return limit;
	}
}

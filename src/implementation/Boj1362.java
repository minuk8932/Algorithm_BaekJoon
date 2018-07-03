package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1362번: 펫
 *
 *	@see https://www.acmicpc.net/problem/1362/
 *
 */
public class Boj1362 {
	private static final String RETURN = "0 0";
	private static final String BREAK = "# 0";
	
	private static final String HAPPY = " :-)";
	private static final String SAD = " :-(";
	private static final String DEAD = " RIP";
	
	private static final String FEED = "F";
	private static final String EXERCISE = "E";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int idx = 0;
		
		while(true) {
			String outline = br.readLine();
			if(RETURN.equals(outline)) break;		// 0 0 이면 바깥 반복문 종료
			
			boolean isAlive = true;					// 펫이 살아있는지 확인하는 변수
			
			StringTokenizer st = new StringTokenizer(outline);
			int o = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			while(true) {
				String inline = br.readLine();
				if(BREAK.equals(inline)) break;		// # 0 이면 내부 반복문 종료
				
				st = new StringTokenizer(inline);
				String process = st.nextToken();
				int value = Integer.parseInt(st.nextToken());
				
				if(FEED.equals(process) && isAlive) {		// 펫이 아직 살아있고 먹이를 주는 경우
					w += value;
				}
				
				if(EXERCISE.equals(process) && isAlive) {	// 펫이 아직 살아있고 운동을 시키는 경우
					w -= value;
				}
				
				if(w <= 0) isAlive = false;		// 몸무게가 0이하로 떨어진경우 사망
			}
			
			idx++;
			sb.append(idx);
			
			if(!isAlive) {
				sb.append(DEAD).append(NEW_LINE);		// 사망한 경우
				continue;
			}
			
			if(w > (o / 2) && w < (o * 2)) sb.append(HAPPY);	// 적정 몸무게의 1/2 ~ 2배인 경우
			else sb.append(SAD);					// 그 외를 각각 버퍼에 담고
			
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
}

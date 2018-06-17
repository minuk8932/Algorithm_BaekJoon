package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15702번: 중간고사 채점
 *
 *	@see https://www.acmicpc.net/problem/15702/
 *
 */
public class Boj15702 {
	private static final int INF = 100_001;
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] exam = new int[INF];
		boolean[] isAttend = new boolean[INF];
		int[] score = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			score[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			for(int j = 0; st.hasMoreTokens(); j++) {
				String result = st.nextToken();
				isAttend[num] = true;			// 해당 수험자 출석 여부
				
				if("O".equals(result)) {		// 답을 맞춘 경우
					exam[num] += score[j];		// 배점을 해당 수험자의 번호에 더해줌
				}
			}
		}
		
		int max = 0;
		for(int i = 1; i < INF; i++) {
			if(isAttend[i]) max = Math.max(max, exam[i]);		// 출석 한 인원에 한에 최댓값을 구함 
		}
		
		for(int i = 1; i < INF; i++) {
			if(max == exam[i] && isAttend[i]) {				// 최댓값을 가지며 출석한 인원인 경우
				System.out.println(i + SPACE + exam[i]);		// 결과를 출력 후 종료
				
				break;
			}
		}
	}
}

package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2852번: NBA 농구
 *
 *	@see https://www.acmicpc.net/problem/2852/
 *
 */
public class Boj2852 {
	private static final int OVER = 2_880;
	private static final String COLON = ":";
	private static final String NEW_LINE = "\n";
	
	private static class Play{
		int team;
		int time;
		
		public Play(int team, int time) {
			this.team = team;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Play[] info = new Play[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int teamNo = Integer.parseInt(st.nextToken());
			
			String timer = st.nextToken();
			st = new StringTokenizer(timer, COLON);
			
			int totalTime = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
			info[i] = new Play(teamNo, totalTime);
		}
		
		System.out.println(getResult(N, info));
	}
	
	private static StringBuilder getResult(int n, Play[] arr) {
		StringBuilder sb = new StringBuilder();
		Play start = new Play(arr[0].team, arr[0].time);
		
		int[] score = new int[2];
		score[arr[0].team - 1] = 1;
		int flag = arr[0].team - 1;
		
		int[] time = new int[2];
		
		for(int i = 1; i < arr.length; i++) {
			score[arr[i].team - 1]++;
			
			if(score[0] == score[1]) {								// 무승부되는 순간 이기던 팀의 시간 합
				if(flag != 2) time[flag] += arr[i].time - start.time;
				
				flag = 2;
				continue;
			}
			
			if(score[0] > score[1]) {		// 1 또는 2팀이 이기기 시작할 때를 클래스 변수에 저장
				if(flag == 0) continue;
				
				start = arr[i];
				flag = 0;
			}
			else {
				if(flag == 1) continue;
				
				start = arr[i];
				flag = 1;
			}
		}
		
		if(flag != 2) time[flag] += OVER - start.time;
		int[] hour = {time[0] / 60, time[1] / 60};
		int[] min = {time[0] % 60, time[1] % 60};
		
		return sb.append(hour[0] < 10 ? "0" + hour[0]: hour[0]).append(COLON).append(min[0] < 10 ? "0" + min[0]: min[0]).append(NEW_LINE)
				.append(hour[1] < 10 ? "0" + hour[1]: hour[1]).append(COLON).append(min[1] < 10 ? "0" + min[1]: min[1]);
	}
}

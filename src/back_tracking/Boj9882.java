package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 9882번: Balanced Teams
 *
 *	@see https://www.acmicpc.net/problem/9882/
 *
 */
public class Boj9882 {
	private static int result = Integer.MAX_VALUE;
	
	private static int[] player = new int[12];
	private static int[] teamNum = new int[12];
	private static int[] teams = new int[4];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < 12; i++) {
			player[i] = Integer.parseInt(br.readLine());
		}
		
		backTracking(0);				// 팀이 나오는 경우의 수
		System.out.println(result);
	}
	
	private static void backTracking(int count) {
		if(count == 12) {
			getMinimum();				// 팀 배정이 끝나면 최소 차이를 구함
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(teams[i] == 3) continue;
			teamNum[count] = i;			// count번째 소의 팀 번호
			teams[i]++;					// 해당 팀 인원수 +1
			
			backTracking(count + 1);
			teams[i]--;					// 팀 인원수 -1, 백트래킹
		}
	}
	
	private static void getMinimum() {
		int[] seq = new int[4];
		
		for(int i = 0; i < 12; i++) {
			seq[teamNum[i]] += player[i];
		}
		
		int max = 0, min = Integer.MAX_VALUE;
		for(int i = 0; i < 4; i++) {
			if(seq[i] > max) max = seq[i];
			if(seq[i] < min) min = seq[i];
		}

		result = Math.min(result, max - min);
	}
}

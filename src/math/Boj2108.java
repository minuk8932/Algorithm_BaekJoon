package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *  백준 2108번: 통계학
 *  
 *  @see https://www.acmicpc.net/problem/2108/
 *  
 */
public class Boj2108 {
	private static final int INF = 9_000;
	private static final int HALF = 4_001;
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] arg) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int max = -INF, min = INF;
		double avg = 0;
		int[] num = new int[N];
		int[][] common = new int[2][HALF];
		
		for(int i = 0; i < N; i++) {
			int a = Integer.parseInt(br.readLine());
			
			avg += (double) a;
			num[i] = a;
			
			if(a > 0) common[1][a]++;		// 양수
			else common[0][Math.abs(a)]++;	// 음수
			
			if(a > max) max = a;
			if(a < min) min = a;
		}
		
		Arrays.sort(num);
		avg /= N;				// 평균
		int mid = num[N / 2];	// 중앙
		int range = max - min;	// 범위
		
		max = 0;
		for(int j = 0; j < 2; j++) {
			for(int i = 0; i < HALF; i++) {
				if(common[j][i] == 0) continue;
				if(max < common[j][i]) max = common[j][i];	// 최빈값에 해당하는 수치 뽑기
			}
		}
		
		int idx = 0, loop = 0;
		
		for(int i = HALF - 1; i >= 0; i--) {	// 음수의 경우 최빈값 찾기
			if(max == common[0][i]) {
				idx = -i;
				loop++;

				if(loop == 2) break;
			}
		}
		
		if(loop != 2) {						// 음수에서 찾지 못한 경우
			for(int i = 0; i < HALF; i++) {		// 양수의 경우 최빈값 찾기
				if(max == common[1][i]) {
					idx = i;
					loop++;

					if(loop == 2) break;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();			// 버퍼에 값들을 담고
		sb.append(Math.round(avg)).append(NEW_LINE);	// 반올림
		sb.append(mid).append(NEW_LINE);
		sb.append(idx).append(NEW_LINE);
		sb.append(range).append(NEW_LINE);
		
		System.out.println(sb.toString());			// 결과값 한번에 출력
	}
}

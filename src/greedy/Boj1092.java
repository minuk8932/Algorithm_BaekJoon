package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 * 백준 1092번: 배
 * 
 * @see https://www.acmicpc.net/problem/1092/
 *
 */
public class Boj1092 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] crain = new int[N];
		for(int i = 0; i < N; i++) {
			crain[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		int[] box = new int[M];
		for(int i = 0; i < M; i++) {
			box[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(getDay(N, M, crain, box));
	}
	
	private static int getDay(int n, int m, int[] c, int[] b) {		
		Arrays.sort(c);
		Arrays.sort(b);
		
		if(c[n - 1] < b[m - 1]) return -1;		// 화물을 모두 옮기진 못하는 경우
		int[] move = new int[n];
		int idx = 0;
		
		
		for(int i = 0; i < m;) {
			if(c[idx] < b[i]) {
				idx++;
				continue;
			}
			
			move[idx]++;					// 적재 하중이 작은 크레인이 최대 몇개 옮길 수 있는가
			i++;
		}
		
		
		int loop = 0, day = 0;

		while(loop < m) {
			for(int cur = 0; cur < n; cur++) {		// 크레인이 옮길 수 있는 갯수가 남은 경우
				if(move[cur] > 0) {
					move[cur]--;
					loop++;
				}
				else {					
					for(int prev = cur - 1; prev >= 0; prev--) {		// 더 이상 옮길것이 없다면, 적재하중이 적은 크레인의 박스를 뺏어 옮김
						if(move[prev] > 0) {
							move[prev]--;
							loop++;
							
							break;
						}
					}
				}
			}
			
			day++;			// 날짜++
		}
		
		return day;
	}
}

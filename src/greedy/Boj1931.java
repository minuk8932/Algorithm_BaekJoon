package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1931번: 회의실 배정
 *
 *	@see https://www.acmicpc.net/problem/1931/
 *
 */
public class Boj1931 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		TimeTable[] tt = new TimeTable[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			tt[i] = new TimeTable(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(tt);		// 시간별 정렬
		
		int max = 1;
		int comp = tt[0].end;
		
		for (int i = 1; i < N; i++) {
			if (tt[i].start == tt[i].end) {		// 시작과 종료 시간이 같은 경우
				max++;
			}
			
	        else if (tt[i].start >= comp) {		// 시작 시간과 저장되어있는 종료 시간의 차이가 0보다 큰 경우
				max++;						// 회의실 배정 +1
				comp = tt[i].end;			// 현재 시작할 회의의 종료시간을 저장
			}
		}
		
		System.out.println(max);		// 최대로 배정할 수 있는 회의실 갯수 출력
	}
	
	private static class TimeTable implements Comparable<TimeTable> {
		int start;
		int end;
		
		public TimeTable(int start, int end) {	// 시작과 종료 시간을 가지는 클래스
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(TimeTable tt) {	// 종료 시간에 따라 오름차순
			if(this.end < tt.end) {
				return -1;
			}
			else if(this.end > tt.end) {
				return 1;
			}
			else {									// 종료 시간이 같다면, 시작 시간 오름차순
				if(this.start < tt.start) return -1;
				else if(this.start > tt.start) return 1;
				else return 0;
			}
		}
	}
}

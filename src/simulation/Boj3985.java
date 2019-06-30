package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 3985번: 롤 케이크
 *
 *	@see https://www.acmicpc.net/problem/3958/
 *
 */
public class Boj3985 {
	private static final String NEW_LINE = "\n";
	
	private static class Range{
		int from;
		int to;
		
		public Range(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		
		Range[] person = new Range[N];
		int max = 0, idx = -1;
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			person[i] = new Range(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
			
			int diff = person[i].to - person[i].from;
			if(diff > max) {							// 예상 최대 방청객
				if(max != diff) idx = i;
				max = diff;
			}
		}
		
		System.out.println(idx + 1 + NEW_LINE + getResult(L, N, person));
	}
	
	private static int getResult(int l, int n, Range[] r) {
		int[] roll = new int[l];
		
		for(int i = 0; i < n; i++) {
			for(int give = r[i].from; give < r[i].to; give++) {				
				if(roll[give] != 0) continue;					// 이미 가져간 범위는 제외
				roll[give] = (i + 1);
			}
		}
		
		int[] get = new int[n + 1];		
		for(int i = 0; i < l; i++) {
			get[roll[i]]++;				// 각 인원이 가져간 조각의 수
		}
		
		int max = 0;
		for(int i = 1; i < n + 1; i++) {
			if(max < get[i]) max = get[i];
		}
		
		int real = 0;
		for(int i = 1; i < n + 1; i++){
			if(get[i] == max) {			// 실제 최대 방청객
				real = i;
				break;
			}
		}
		
		return real; 
	}
}

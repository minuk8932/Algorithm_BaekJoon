package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17488번: 수강 바구니
 *
 *	@see https://www.acmicpc.net/problem/17488/
 *
 */
public class Boj17488 {
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	private static final String NONE = "망했어요";
	
	private static ArrayList<Integer>[] result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		ArrayList<Integer>[] student = new ArrayList[N];
		result = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			student[i] = new ArrayList<>();
			result[i] = new ArrayList<>();
		}
		
		int[] limit = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			limit[i] = Integer.parseInt(st.nextToken());
		}
		
		int loop = 2 * N;
		int num = 0;
		
		while(loop-- > 0) {
			st = new StringTokenizer(br.readLine());
			num %= N;
			
			while(st.hasMoreTokens()) {
				int idx = Integer.parseInt(st.nextToken()) - 1;
				if(idx == -2) break;				// end
				
				limit[idx]--;						// personnel limit decrease
				student[num].add(idx + 1);
			}
			
			num++;
			
			if(loop % N == 0) {						// 1st, 2nd request saved
				setting(N, student, limit);
				
				student = new ArrayList[N];
				for(int i = 0; i < N; i++) {
					student[i] = new ArrayList<>();
				}
			}
		}
		
		System.out.println(getStatus(N, limit));
	}
	
	private static void setting(int n, ArrayList<Integer>[] sub, int[] stop) {
		for(int i = 0; i < n; i++) {
			for(int lec: sub[i]) {
				if(stop[lec - 1] < 0) continue;
				result[i].add(lec);
			}
		}
	}
	
	private static String getStatus(int n, int[] stop) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < n; i++) {
			if(result[i].isEmpty()) sb.append(NONE);
			else {
				Collections.sort(result[i]);			// asc
				
				for(int lec: result[i]) {
					sb.append(lec).append(SPACE);
				}
			}
			
			sb.append(NEW_LINE);
		}
		
		return sb.toString();
	}
}

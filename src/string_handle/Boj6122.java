package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6122번: Time Cards
 *
 *	@see https://www.acmicpc.net/problem/6122/
 *
 */
public class Boj6122 {
	private static ArrayList<Info>[] list;
	
	private static final String SRT = "START";
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static class Info{
		String keyword;
		int hour;
		int minute;
		
		public Info(String keyword, int hour, int minute) {
			this.keyword = keyword;
			this.hour = hour;
			this.minute = minute;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int NLines = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		
		while(NLines-- > 0) {							// offline
			st = new StringTokenizer(br.readLine());
			list[Integer.parseInt(st.nextToken()) - 1].add(new Info(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		System.out.println(spendTime(N));
	}
	
	private static StringBuilder spendTime(int n) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < n; i++) {
			int h = 0, m = 0;
			int totalTimer = 0;
			
			for(Info cows: list[i]) {				// i번 소의 총 사용시간
				if(cows.keyword.equals(SRT)) {
					h = cows.hour;
					m = cows.minute;
				}
				else {
					h = cows.hour - h;
					m = cows.minute - m;
					
					if(m < 0) {
						m += 60;
						h--;
					}
					
					totalTimer += h * 60 + m;
				}
			}
			
			sb.append(totalTimer / 60).append(SPACE).append(totalTimer % 60).append(NEW_LINE);
		}
		
		return sb;
	}
}

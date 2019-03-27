package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15595번: 정답 비율 계산하기
 *
 *	@see https://www.acmicpc.net/problem/15595/
 *
 */
public class Boj15595 {
	private static final String ADMIN = "megalusion";
	
	private static class Status{
		int report;
		boolean accepted;
		
		public Status(int report, boolean accepted) {
			this.report = report;
			this.accepted = accepted;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		String[] lines = new String[N];
		for(int i = 0; i < N; i++) {
			lines[i] = br.readLine();
		}
		
		System.out.println(getAcceptedRate(N, lines));
	}
	
	private static double getAcceptedRate(int n, String[] str) {
		HashMap<String, Status> hm = new HashMap<>();
		double result = 0, report = 0;
		
		for(String rep: str) {
			StringTokenizer st = new StringTokenizer(rep);
			st.nextToken();
			
			String id = st.nextToken();
			int res = Integer.parseInt(st.nextToken());
			
			
			if(id.equals(ADMIN)) continue;
			
			if(hm.containsKey(id)) {
				if(hm.get(id).report == -1) continue;					// 이미 정답 처리 받은 사용자
				
				if(res == 4) hm.put(id, new Status(hm.get(id).report + 1, true));
				else hm.put(id, new Status(hm.get(id).report + 1, false));
			}
			else {
				if(res == 4) hm.put(id, new Status(1, true));
				else hm.put(id, new Status(1, false));
			}
			
			if(hm.get(id).accepted) {			// 정답을 받은 사용자 연산 후 -1로 예외처리
				result += 1;
				report += hm.get(id).report;
				
				hm.put(id, new Status(-1, true));
			}
		}
		
		return report == 0 ? 0: result / report * 100.0;
	}
}

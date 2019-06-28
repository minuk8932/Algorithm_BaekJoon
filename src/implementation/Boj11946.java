package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 11946번: ACM-ICPC
 *
 *	@see https://www.acmicpc.net/problem/11946/
 *
 */
public class Boj11946 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final String ACCEPT = "AC";
	
	private static class Test{
		int time;
		boolean result;
		
		public Test(int time, boolean result) {
			this.time = time;
			this.result = result;
		}
	}
	
	private static class Result implements Comparable<Result>{
		int sol;
		int totalTime;
		int team;
		
		public Result(int sol, int totalTime, int team) {
			this.sol = sol;
			this.totalTime = totalTime;
			this.team = team;
		}

		@Override
		public int compareTo(Result r) {
			if(this.sol > r.sol) {
				return -1;
			}
			else if(this.sol < r.sol) {
				return 1;
			}
			else {
				if(this.totalTime < r.totalTime) {
					return -1;
				}
				else if(this.totalTime > r.totalTime) {
					return 1;
				}
				else {
					if(this.team < r.team) return -1;
					else if(this.team > r.team) return 1;
					else return 0;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		ArrayList<Test>[][] icpc = new ArrayList[n][m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				icpc[i][j] = new ArrayList<>();
			}
		}
		
		while(q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			int tNum = Integer.parseInt(st.nextToken()) - 1;
			int pNum = Integer.parseInt(st.nextToken()) - 1;
			String res = st.nextToken();
			
			icpc[tNum][pNum].add(new Test(time, res.equals(ACCEPT)));
		}
		
		System.out.print(getRank(n, m, icpc));
	}
	
	private static String getRank(int n, int m, ArrayList<Test>[][] list) {
		StringBuilder sb = new StringBuilder();
		int[][] total = new int[n][m];
		
		for(int team = 0; team < n; team++) {					// i번 팀의
			for(int problem = 0; problem < m; problem++) {		// j번 문제
				int times = 0, fail = 0;
				boolean solve = false;
				
				for(Test info: list[team][problem]) {
					times = info.time;							// 최종 해결 시간
					
					if(info.result) {
						solve = true;							// AC 여부
						break;
					}
					fail++;
				}
				
				if(solve) total[team][problem] = (fail * 20) + times;		// 해결한 경우
				else total[team][problem] = -1;
			}
		}
		
		Result[] res = getResult(n, m, total);
		for(int i = 0; i < res.length; i++) {
			sb.append(res[i].team + 1).append(SPACE).append(res[i].sol).append(SPACE).append(res[i].totalTime).append(NEW_LINE);
		}
		
		return sb.toString();
	}
	
	private static Result[] getResult(int n, int m, int[][] arr) {
		Result[] res = new Result[n];
		
		for(int i = 0; i < n; i++) {
			int count = 0;
			int time = 0;
			
			for(int j = 0; j < m; j++) {
				if(arr[i][j] == -1) continue;		// 해결 못한 문제: 결과에 포함x
				count++;							// 문제수
				time += arr[i][j];					// 걸린 총 시간
			}
			
			res[i] = new Result(count, time, i);
		}
		
		Arrays.sort(res);							// 조건 별 정렬
		return res;
	}
}

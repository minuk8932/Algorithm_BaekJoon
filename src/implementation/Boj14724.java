package implementation;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;

/**
 * 
 * 	@author exponential-e
 *	백준 14724번: 관리자는 누구?
 *
 *	@see https://www.acmicpc.net/problem/14724/
 *
 */
public class Boj14724 {
	private static final String[] GROUP = {"PROBRAIN", "GROW", "ARGOS", "ADMIN", "ANT", "MOTION", "SPG", "COMON", "ALMIGHTY"};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int max = 0, idx = 0;
		for(int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				int cnt = Integer.parseInt(st.nextToken());
				
				if(cnt > max) {			// 관리자 동아리 체크
					max = cnt;
					idx = i;
				}
			}
		}
		
		System.out.println(GROUP[idx]);
	}
}

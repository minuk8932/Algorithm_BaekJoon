package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1244번: 스위치 켜고 끄기
 *
 *	@see https://www.acmicpc.net/problem/1244/
 *
 */
public class Boj1244 {
	private static boolean[] bulb;
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		int N = Integer.parseInt(br.readLine());
		
		bulb = new boolean[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i < N + 1; i++) {
			bulb[i] = Integer.parseInt(st.nextToken()) == 1? true: false;
		}
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int index = Integer.parseInt(st.nextToken());
			
			if(gender == 1) {
				male(N, index);		// 소년 방식
			}
			else {
				female(N, index);	// 소녀 방식
			}
		}
		
		System.out.print(getResultState());
	}
	
	private static void male(int n, int idx) {
		int mul = 1;
		
		while(idx * mul <= n) {
			bulb[idx * mul] = !bulb[idx * mul];
			mul++;
		}
	}
	
	private static void female(int n, int idx) {
		int spread = 0;
		
		while(idx - spread > 0 && idx + spread <= n) {
			if(bulb[idx - spread] == bulb[idx + spread]) {
				bulb[idx - spread] = bulb[idx + spread] = !bulb[idx - spread];
				spread++;
			}
			else {
				break;
			}
		}
	}
	
	private static StringBuilder getResultState() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < bulb.length; i++) {
			if(i % 20 == 0) sb.append(bulb[i] ? 1 : 0).append(NEW_LINE);		// 문제 조건
			else sb.append(bulb[i] ? 1 : 0).append(SPACE);
		}
		
		return sb;
	}
}

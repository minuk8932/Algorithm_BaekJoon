package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17264번: I AM IRONMAN
 *
 *	@see https://www.acmicpc.net/problem/17264/
 *
 */
public class Boj17264 {
	private static HashMap<String, Boolean> userInfo = new HashMap<>();
	
	private static final String WIN = "W";
	private static final String ESC = "I AM NOT IRONMAN!!";
	private static final String NOT_ESC = "I AM IRONMAN!!";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		
		while(P-- > 0) {
			st = new StringTokenizer(br.readLine());
			userInfo.put(st.nextToken(), st.nextToken().equals(WIN) ? true: false);
		}
		
		String[] playing = new String[N];
		for(int i = 0; i < N; i++) {
			playing[i] = br.readLine();
		}
		
		System.out.println(canEscape(N, W, L, G, playing));
	}
	
	private static String canEscape(int n, int win, int lose, int goal, String[] name) {
		int score = 0;
		
		for(int i = 0; i < n; i++) {
			if(userInfo.containsKey(name[i])) {		// 해킹 가능
				if(userInfo.get(name[i])) score += win;
				else score = downScore(score, lose);
			}
			else {									// 해킹 불가 선수
				score = downScore(score, lose);
			}
			
			if(score >= goal) break;
		}
		
		return goal <= score ? ESC: NOT_ESC;
	}
	
	private static int downScore(int result, int value) {
		int diff = result - value;
		return diff < 0 ? 0: diff;
	}
}

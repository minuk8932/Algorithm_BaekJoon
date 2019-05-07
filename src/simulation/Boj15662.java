package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15662번: 톱니바퀴 2
 *
 *	@see https://www.acmicpc.net/problem/15662/
 *
 */
public class Boj15662 {	
	private static boolean[][] chain;
	private static boolean[] isCCW;
	private static boolean[] notTurn;
	
	private static int T;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		chain = new boolean[T][8];
		isCCW = new boolean[T];
		
		for(int i = 0; i < T; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < 8; j++) {
				if(line.charAt(j) == '1') chain[i][j] = true;
				else chain[i][j] = false;
			}
		}
		
		int k = Integer.parseInt(br.readLine());
		
		while(k-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int target = Integer.parseInt(st.nextToken()) - 1;
			
			isCCW[target] = Integer.parseInt(st.nextToken()) == -1 ? true : false;
			notTurn = new boolean[T];
			
			init(target);
			getState(target);
		}
		
		System.out.println(getScore());
	}
	
	private static int getScore() {
		int score = 0;
		
		for(int i = 0; i < T; i++) {
			if(chain[i][0]) score++;
		}
		
		return score;
	}
	
	private static void init(int loop) {
		for(int i = loop - 1; i >= 0; i--) {
			isCCW[i] = !isCCW[i + 1];									// 시작 톱니바퀴 기준 각각 방향 설정
			
			if(chain[i + 1][6] == chain[i][2]) notTurn[i] = true;		// 돌 수 없는 톱니바퀴 체크
		}
		
		for(int i = loop + 1; i < T; i++) {
			isCCW[i] = !isCCW[i - 1];
			
			if(chain[i - 1][2] == chain[i][6]) notTurn[i] = true;
		}
	}
	
	private static void getState(int number) {		
		turnIncrease(number);					// 기준 톱니바퀴 보다 번호가 큰 경우
		turnDecrease(number);					// 기준 톱니바퀴 보다 번호가 작은 경우
		turnCurrent(number);					// 기준 톱니바퀴
	}
	
	private static void turnCurrent(int current) {
		if(isCCW[current]) {
			boolean prev = chain[current][0];			// 맨 앞 저장 후 앞으로 하나씩 당기기
			
			for(int i = 1; i < 8; i++) {
				chain[current][i - 1] = chain[current][i];
			}
			
			chain[current][7] = prev;
		}
		else {
			boolean post = chain[current][7];
			
			for(int i = 6; i >= 0; i--) {				// 맨 뒤 저장 후 뒤로 하나씩 밀기
				chain[current][i + 1] = chain[current][i];
			}
			
			chain[current][0] = post;
		}
	}
	
	private static void turnIncrease(int start){
		for(int idx = start + 1; idx < T; idx++) {
			if(notTurn[idx]) return;
			turnCurrent(idx);
		}
	}
	
	private static void turnDecrease(int start) {
		for(int idx = start - 1; idx >= 0; idx--) {
			if(notTurn[idx]) return;			
			turnCurrent(idx);
		}
	}
}

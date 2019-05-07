package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11366번: Tons of Orcs, no Fibbin’
 *
 *	@see https://www.acmicpc.net/problem/11366/
 *
 */
public class Boj11366 {
	private static final int[] FIBO = new int[47];
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();	
		makeCoefiicient();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a == 0 && b == 0 && c == 0) break;
			sb.append(getOrcCount(a, b, c)).append(NEW_LINE);
		}
		
		System.out.print(sb);
	}
	
	private static void makeCoefiicient() {			// n번째 피보나치항 ax + by 의 계수 a, b 를 미리구해둠, 단 a == b - 1
		FIBO[0] = FIBO[1] = 1;
		
		for(int i = 2; i < FIBO.length; i++) {
			FIBO[i] = FIBO[i - 1] + FIBO[i - 2];
		}
	}
	
	private static int getOrcCount(int x, int y, int day) {
		if(day == 1) return x + y;								// 1일인 경우 x의 계수에 해당하는 배열 인덱스가 음수가 될 수 있음
		if(x == 0 && y == 0) return 0;							// 만약 x, y모두가 0이라면, 이후는 볼 필요가 없음
		
		return x * FIBO[day - 1] + y * FIBO[day];				// 피보나치 n번째 항 일반화
	}
}

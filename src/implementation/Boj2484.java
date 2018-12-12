package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2484번: 주사위 네개
 *
 *	@see https://www.acmicpc.net/problem/2484/
 *
 */
public class Boj2484 {
	private static final int[] BASIC_PRIZE = {0, 0, 1000, 10000, 50000};
	private static final int[] ADDITION_PRIZE = {0, 100, 100, 1000, 5000};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int max = 0;
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int[] dice = new int[7];
			for(int i = 0; i < 4; i++) {
				dice[Integer.parseInt(st.nextToken())]++;
			}
			
			int value = isStrike(dice);
			max = getMax(max, value);
		}
		
		System.out.println(max);
	}
	
	private static int isStrike(int[] arr) {
		boolean[] isVisited = new boolean[5];
		int strikeCounts = 0;
		int dot = 2;
		int oneMore = -1;
		
		for(int i = 4; i > 1; i--) {					// 4 ~ 2개 중복시
			for(int j = 1; j < arr.length; j++) {
				if(arr[j] == i) {
					if(i == 2) {
						if(isVisited[i]) oneMore = j;	// 2, 2 중복시
						else dot = j;
					}
					else {
						dot = j;
					}
					
					isVisited[i] = true;
					strikeCounts = i;
				}
			}
			
			if(dot != 2) {
				if(strikeCounts != 0) break;
			}
		}
		
		if(strikeCounts == 0) {							// 주사위가 모두 다른 수인 경우
			dot = oneStrike(arr);
			strikeCounts = 1;
		}
		
		return getScore(dot, strikeCounts, oneMore);
	}
	
	private static int oneStrike(int[] arr) {
		for(int i = arr.length - 1; i > 0; i--) {
			if(arr[i] == 1) {
				return i;
			}
		}
		
		return 0;
	}
	
	private static int getScore(int d, int sc, int spare) {
		int prize = 0;
		
		if(spare == -1) {
			prize = BASIC_PRIZE[sc] + d * ADDITION_PRIZE[sc];	
		}
		else {
			prize = BASIC_PRIZE[sc] * 2 + (d + spare) * ADDITION_PRIZE[sc] * 5;
		}
		
		return prize;
	}
	
	private static int getMax(int a, int b) {
		if(a > b) return a;
		else return b;
	}
}

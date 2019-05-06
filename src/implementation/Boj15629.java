package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * 
 * 	@author minchoba
 *	백준 15629번: Africa
 *
 *	@see https://www.acmicpc.net/problem/15629/
 *
 */
public class Boj15629 {
	private static HashMap<String, Integer> country = new HashMap<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		init();
		
		boolean[] enter = new boolean[9];
		int[] sequence = new int[9];
		for(int i = 0; i < N; i++) {
			int idx = country.get(br.readLine());
			enter[idx] = true;
			sequence[idx] = i;		// 방문 순서
		}
		
		System.out.println(getCost(enter, sequence));
	}
	
	private static void init() {
		country.put("botswana", 1);
		country.put("ethiopia", 2);
		country.put("kenya", 3);
		country.put("namibia", 4);
		country.put("south-africa", 5);
		country.put("tanzania", 6);
		country.put("zambia", 7);
		country.put("zimbabwe", 8);
	}
	
	private static int getCost(boolean[] visa, int[] seq) {
		int cost = 0;
		
		if(visa[2]) cost += 50;
		if(visa[3]) cost += 50;
		if(visa[4]) {
			if(visa[5] && seq[5] < seq[4]) cost += 40;			// 남아공을 들려 나미비아 비자를 발급
			else cost += 140;									// 대행사를 통한 비자 발급
		}
		
		if(visa[6]) cost += 50;
		if(visa[7] || visa[8]) {
			if(visa[7] && visa[8]) {
				if((Math.abs(seq[7] - seq[8]) == 1)) cost += 50;	// 잠비아와 짐바브웨를 동시에 방문 할 경우
				else cost += 80;									// 잠비아 짐바브웨 모두 방문은 하지만 일정이 겹치지 않는 경우
			}
			else {
				if(visa[7]) cost += 50;								// 잠비아만 방문
				else cost += 30;									// 짐바브웨만 방문
			}
		}
		
		return cost;
	}
}

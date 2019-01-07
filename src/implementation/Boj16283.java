package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16283번: Farm
 *
 *	@see https://www.acmicpc.net/problem/16283/
 *
 */
public class Boj16283 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		
		int sheep = getSheep(a, b, n, w);
		int goat = n - sheep;
		
		System.out.println(sheep == -1 ? -1 : sheep + " " + goat);		// 결과 출력
	}
	
	private static int getSheep(int sEat, int gEat, int aniSum, int eatSum) {	
		int count = 0;
		int sCount = -1;
		
		for(int i = 1; i < aniSum; i++) {
			int total = (sEat * i) + (gEat * (aniSum - i));		// 염소와 양이 먹은 총 사료의 합 방정식

			if(total == eatSum) {
				count++;
				sCount = i;
			}
		}
		
		if(count >= 2) return -1;		// 염소와 양의 수가 여러개인 경우
		else return sCount;
	}
}

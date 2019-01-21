package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16785번: ソーシャルゲーム
 *
 *	@see https://www.acmicpc.net/problem/16785/
 *
 */
public class Boj16785 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int coin = 0;
		int days = 0;
		
		while(coin < C) {
			days++;
			if(days % 7 == 0) coin += B;		// 7일마다 추가 코인
			coin += A;
		}
		
		System.out.println(days);
	}
}

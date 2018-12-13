package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 4299번: AFC 윔블던
 *
 *	@see https://www.acmicpc.net/problem/4299/
 *
 */
public class Boj4299 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		result(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
	}
	
	private static void result(int sum, int sub) {
		int win = (sum + sub) / 2;
		int lose = sum - win;
		
		if(win + lose == sum && win - lose == sub) {
			if(lose < 0) {
				System.out.println(-1);
			}
			else {
				if(win > lose) {
					System.out.println(win + " " + lose);
				}
				else {
					System.out.println(lose + " " + win);
				}
			}
		}
		else {
			System.out.println(-1);
		}
	}
}

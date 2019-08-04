package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 13224번: Chop cup
 *
 *	@see https://www.acmicpc.net/problem/13224/
 *
 */
public class Boj13224 {
	private static int pos;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] routine = br.readLine().toCharArray();
		
		chopping(routine);
		System.out.println(pos + 1);
	}
	
	private static void chopping(char[] arr) {		
		for(char c: arr) {
			if(c == 'A') change(0, 1);
			else if(c == 'B') change(1, 2);
			else change(0, 2);
		}
	}
	
	private static void change(int x, int y) {		// 위치 섞기
		if(x != pos && y != pos) return;
		
		if(x == pos) pos = y;
		else pos = x;
	}
}

package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2716번: 원숭이 매달기
 *
 *	@see https://www.acmicpc.net/problem/2716/
 *
 */
public class Boj2716 {
	private static final char PREV = '[';
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			char[] vines = br.readLine().toCharArray();			
			int count = 0, max = 0;
			
			for(char c: vines) {
				if(c == PREV) {
					count++;
					
					if(max < count) max = count;		// 노드의 최대 level
				}
				else {
					count--;
				}
			}
			
			sb.append((int) Math.pow(2, max)).append(NEW_LINE);		// level에 따라 제곱수로 원숭이 수가 증가
		}
		
		System.out.println(sb);
	}
}

package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * 
 * 	@author minchoba
 *	백준 5397번: 키로거
 *
 *	@see https://www.acmicpc.net/problem/5397/
 *
 */
public class Boj5397 {
	private static final char NEW_LINE = '\n';
	private static int cursor;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			LinkedList<Character> pw = new LinkedList<>();
			cursor = 0;
			
			for(char c: br.readLine().toCharArray()) {				
				if(c == '-') {
					if(pw.size() == 0 || cursor == 0) continue;
					pw = remove(pw);
				}
				else if(c == '>') {
					if(cursor == pw.size() || pw.size() == 0) continue;
					cursor++;
				}
				else if(c == '<') {
					if(cursor == 0) continue;
					cursor--;
				}
				else {
					pw.add(cursor, c);
					if(pw.size() != 0 || cursor != pw.size()) cursor++;
				}
			}
			
			sb.append(getString(pw)).append(NEW_LINE);
		}
		
		br.close();
		System.out.println(sb);
	}
	
	private static LinkedList<Character> remove(LinkedList<Character> ll){
		ll.remove(cursor == 0 ? cursor : cursor - 1);					// 커서가 0인 경우를 제외하고 이전 문자열 삭제
		if(cursor != 0) cursor--;
		
		return ll;
	}
	
	private static StringBuilder getString(LinkedList<Character> tmp) {		// 결과 문자열
		StringBuilder sb = new StringBuilder();
		
		while(!tmp.isEmpty()) {
			char c = tmp.removeFirst();
			sb.append(c);
		}
		
		return sb;
	}
}
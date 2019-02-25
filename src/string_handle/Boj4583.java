package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 4583번: 거울상
 *
 *	@see https://www.acmicpc.net/problem/4583/
 *
 */
public class Boj4583 {
	private static final char[] MIRRORING = {'b', 'p', 'x', 'w', 'v', 'o', 'i','i', 'o', 'v', 'w', 'x', 'q', 'd'};
	private static final String TERMINATE = "#";
	private static final String NEW_LINE = "\n";
	private static final String WRONG = "INVALID";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String line = br.readLine();
			if(line.equals(TERMINATE)) break;
			
			char[] word = line.toCharArray();
			sb.append(getMirror(word)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static String getMirror(char[] arr) {
		StringBuilder sb = new StringBuilder();
		
		for(char c: arr) {
			boolean reflect = false;
			
			for(int i = 0; i < MIRRORING.length; i++) {
				if(c != MIRRORING[i]) continue;
				reflect = true;
				
				sb.append(MIRRORING[MIRRORING.length - 1 - i]);
				if(reflect) break;			// 해당 단어를 찾은 경우
			}
			
			if(!reflect) return WRONG;		// 해당 단어가 존재하지 않음
		}
		
		return sb.reverse().toString();		// 거울에 비친 모습으로 탈바꿈!
	}
}

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 5698번: Tautogram
 *
 *	@see https://www.acmicpc.net/problem/5698/
 *
 */
public class Boj5698 {
	private static final String STOP = "*";
	private static final char SPACE = ' ';
	
	private static final String TAUTOGRAM = "Y";
	private static final String IS_NOT_TAUTOGRAM = "N";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String line = br.readLine();
			if(STOP.equals(line)) break;
			
			char[] word = line.toCharArray();
			int[] alpha = new int[26];
			
			int cnt = 1;
			int idx = 0;
			if(word[0] >= 'A' && word[0] <= 'Z') idx = word[0] - 65;
			if(word[0] >= 'a' && word[0] <= 'z') idx = word[0] - 97;
			
			alpha[idx]++;
			
			for(int i = 1; i < word.length; i++) {
				if(SPACE != word[i]) continue;

				if(word[i + 1] >= 'A' && word[i + 1] <= 'Z') idx = word[i + 1] - 65;
				if(word[i + 1] >= 'a' && word[i + 1] <= 'z') idx = word[i + 1] - 97;
				alpha[idx]++;
				cnt++;
			}
			
			sb.append(alpha[idx] == cnt ? TAUTOGRAM : IS_NOT_TAUTOGRAM).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
}

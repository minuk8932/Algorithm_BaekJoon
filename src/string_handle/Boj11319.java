package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 11319번: Count Me In
 *
 *	@see https://www.acmicpc.net/problem/11319/
 *
 */
public class Boj11319 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static final String VOWELS = "AEIOUaeiou";
	
	private static class Pair{
		int consonant;
		int vowel;
		
		public Pair(int consonant, int vowel) {
			this.consonant = consonant;
			this.vowel = vowel;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int S = Integer.parseInt(br.readLine());
		
		while(S-- > 0) {
			String line = br.readLine();
			Pair result = seperate(line);
			
			sb.append(result.consonant).append(SPACE).append(result.vowel).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static Pair seperate(String str) {
		int size = str.length();
		int v = 0;
		
		for(char c: str.toCharArray()) {
			String subString = c + "";
			
			if(SPACE.equals(subString)) {				// 공백인 경우 전체 단어의 갯수 감소
				size--;
			}
			else {
				if(VOWELS.contains(subString)) v++;		// 모음 인 경우
			}
		}
		
		return new Pair(size - v, v);
	}
}

package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 9946번: 단어 퍼즐
 *
 *	@see https://www.acmicpc.net/problem/9946/
 *
 */
public class Boj9946 {
	private static final String TERMINATE = "END";
	private static final String CASE = "Case ";
	private static final String SAME = ": same";
	private static final String DIFFERENT = ": different";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int loop = 1;
		
		while(true) {
			String word1 = br.readLine();
			String word2 = br.readLine();
			
			if(word1.equals(TERMINATE) && word2.equals(TERMINATE)) break;
			
			sb.append(CASE).append(loop++).append(result(word1.toCharArray(), word2.toCharArray()) ? SAME: DIFFERENT).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static boolean result(char[] origin, char[] comp) {
		if(isSameArray(charInput(origin, origin.length), charInput(comp, comp.length))) return true;
		else return false;
	}
	
	private static int[] charInput(char[] c, int leng) {
		int[] arr = new int[26];
		
		for(int i = 0; i < leng; i++) {
			int idx = c[i] - 'a';
			arr[idx]++;
		}
		
		return arr;
	}
	
	private static boolean isSameArray(int[] arr1, int[] arr2) {		// 단어 갯수 동일한지
		for(int i = 0; i < 26; i++) {
			if(arr1[i] != arr2[i]) return false;
		}
		
		return true;
	}
}

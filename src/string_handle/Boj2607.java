package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2607번: 비슷한 단어
 *
 *	@see https://www.acmicpc.net/problem/2607/
 *
 */
public class Boj2607 {
	private static int[] alpha = new int[26];
	private static int size = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char[] input = br.readLine().toCharArray();
		size = input.length;
		
		for(char w: input) {
			alpha[w - 'A']++;
		}
		
		int count = 0;
		for(int i = 0; i < N - 1; i++) {
			if(isSimilar(br.readLine())) count++;
		}
		
		System.out.println(count);
	}
	
	private static boolean isSimilar(String str) {
		int[] comp = new int[26];
		for(char w: str.toCharArray()) {
			comp[w - 'A']++;
		}
		
		int diff = 0;
		int leng = 0;
		
		for(int i = 0; i < 26; i++) {
			if(comp[i] != alpha[i]) diff += Math.abs(comp[i] - alpha[i]);		// 차이의 합
			leng += comp[i];
		}
		
		if(diff > 1 && leng != size) return false;		// 길이가 다른데 차이가 2 이상인 경우
		return leng == size && diff > 2 ? false: true;	// 길이가 같은데 차이가 3 이상인 경우, 2이상은 치환 가능
	}
}

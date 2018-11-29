package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10175번: dominant species
 *
 *	@see https://www.acmicpc.net/problem/10175/
 *
 */
public class Boj10175 {
	private static final String[] ANIMAL = {" ", " Bobcat", " Coyote", " ", " ", " ", " ", " ", " ", " ", " ", " ", " Mountain Lion"
			, " ", " ", " ", " ", " ", " ", " ", " ", " ", " Wolf", " ", " ", " "};
	private static final int[] ANIMAL_COST = {0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4
			, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0};
	private static final String COLON = ": ";
	private static final String SENTENCE_1 = "The";
	private static final String SENTENCE_2 = " is the dominant species";
	private static final String SENTENCE_DRAW = "re is no dominant species";
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		
		while(n-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String location = st.nextToken();
			char[] species = st.nextToken().toCharArray();
			
			int res = whoIsTheDominant(species);
			sb.append(location).append(COLON).append(SENTENCE_1)
			.append(res == -1 ? SENTENCE_DRAW: ANIMAL[res] + SENTENCE_2).append(NEW_LINE);
		}
		
		System.out.println(sb);		// 결과 출력
	}
	
	private static int whoIsTheDominant(char[] s){
		int[] alpha = new int[26];
		
		for(char w: s) {
			int idx = w - 'A';
			alpha[idx] += ANIMAL_COST[idx];
		}
		
		int max = getMax(alpha);
		int dominant = getTheMaxIdx(alpha, max);
		
		return dominant;
	}
	
	private static int getMax(int[] arr) {
		int val = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] > val) val = arr[i];
		}
		
		return val;
	}
	
	private static int getTheMaxIdx(int[] arr, int max) {
		int counts = 0, idx = 0;
		
		for(int i = 0; i < arr.length; i++) {
			if(max == arr[i]) {
				counts++;
				idx = i;
			}
		}
		
		return counts == 1 ? idx : -1;
	}
}

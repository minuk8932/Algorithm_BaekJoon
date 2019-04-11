package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * 
 * 	@author minchoba
 *	백준 7578번: Anagrams!
 *
 *	@see https://www.acmicpc.net/problem/7578/
 *
 */
public class Boj7587 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0) break;
			
			String[] input = new String[n];
			int[][] count = new int[n][26];
			
			HashMap<String, Integer> hm = new HashMap<>();
			input[0] = br.readLine();
			hm.put(input[0], 1);
			
			for(char c: input[0].toCharArray()) {
				count[0][c - 'a']++;
			}
			
			for(int i = 1; i < n; i++) {
				input[i] = br.readLine();
				
				for(char c: input[i].toCharArray()) {		// 각 문자열 구성 갯수
					count[i][c - 'a']++;
				}
			}
			
			String res = getBiggest(n, hm, count, input);
			sb.append(res).append(SPACE).append(hm.get(res) - 1).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static boolean isAnagram(int[] arr1, int[] arr2) {
		for(int i = 0; i < 26; i++) {				// 애너그램인가 확인
			if(arr1[i] != arr2[i]) return false;
		}
		
		return true;
	}
	
	private static String getBiggest(int N, HashMap<String, Integer> words, int[][] counts, String[] str) {
		boolean[] visit = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				if(visit[j]) continue;
				
				if(isAnagram(counts[i], counts[j])) {
					visit[i] = true;
					
					if(!words.containsKey(str[i])) {		// 애너그램 중복처리 예비
						words.put(str[i], 1);
					}
					else {									// 애너그램 카운팅
						words.put(str[i], words.get(str[i]) + 1);
					}
				}
				else {						// 처음 등장하는 문자열
					words.put(str[j], 1);
				}
			}
		}
		
		String tmp = "";
		int max = 0;
		
		for(String key: words.keySet()) {
			int cost = words.get(key);
			if(cost > max) {
				max = cost;
				tmp = key;
			}
		}
		
		return tmp;
	}
}

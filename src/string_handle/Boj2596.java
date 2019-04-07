package hashing;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 
 * 	@author minchoba
 *	백준 2596번: 비밀 편지
 *
 *	@see https://www.acmicpc.net/problem/2596/
 *
 */
public class Boj2596 {
	private static HashMap<String, Character> hm = new HashMap<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] letter = new String[N];
		Arrays.fill(letter, "");
		
		char[] input = br.readLine().toCharArray();
		
		for(int i = 0; i < N; i++) {
			int start = i * 6;
			
			for(int j = start; j < start + 6; j++) {
				letter[i] += input[j];
			}
		}
		
		init();
		System.out.println(decoding(N, letter));
	}
	
	private static void init() {
		hm.put("000000", 'A');
		hm.put("001111", 'B');
		hm.put("010011", 'C');
		hm.put("011100", 'D');
		hm.put("100110", 'E');
		hm.put("101001", 'F');
		hm.put("110101", 'G');
		hm.put("111010", 'H');
	}
	
	private static String decoding(int n, String[] arr) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < n; i++) {
			if(hm.containsKey(arr[i])) {			// 바로 해석
				sb.append(hm.get(arr[i]));
			}
			else {
				char[] tmp = arr[i].toCharArray();
				int min = Integer.MAX_VALUE;
				
				for(String str: hm.keySet()) {
					int index = 0;
					int count = 0;
					
					for(char c: str.toCharArray()) {
						if(c != tmp[index++]) count++;
					}
					
					min = Math.min(count, min);
					
					if(count == 1) {				// 1개 차이로 다른 경우
						sb.append(hm.get(str));
						break;
					}
				}
				
				if(min >= 2) return (i + 1) + "";	// 2개 이상 차이나는 경우
			}
		}
		
		return sb.toString();
	}
}

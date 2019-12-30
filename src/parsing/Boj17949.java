package parsing;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17949번: Drop the Byte!
 *
 *	@see https://www.acmicpc.net/problem/17949/
 *
 */
public class Boj17949 {
	private static final HashMap<String, Integer> BYTE = new HashMap<>();
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int n = Integer.parseInt(br.readLine());
		
		init();
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int start = 0;
		
		while(n-- > 0) {
			int size = BYTE.get(st.nextToken()) + start;
			
			sb.append(codeToDecimal(input, start, size)).append(SPACE);
			start = size;
		}
		
		System.out.println(sb.toString());
	}
	
	private static void init() {
		BYTE.put("int", 8);
		BYTE.put("char", 2);
		BYTE.put("long_long", 16);
	}
	
	private static long codeToDecimal(String in, int s, int e) {
		e = Math.min(e, in.length());
		return Long.parseLong(in.substring(s, e), 16);		// Hex to Long
		
//		long res = 0;
//		int pow = 0;
		
//		e = Math.min(e, in.length());						// why wrong implements?
//		
//		for(int i = e - 1; i >= s; i--) {
//			char c = in.charAt(i);
//			long val = 0;
//			
//			if(c >= 'a' && c <= 'f') val = (c - 'a') + 10;
//			else val = c - '0';
//			
//			res += val * Math.pow(16L, pow++);
//		}
//		
//		return res;
	}
}

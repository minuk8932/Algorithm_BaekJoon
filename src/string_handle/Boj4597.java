package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 4597번: 패리티
 *
 *	@see https://www.acmicpc.net/problem/4597/
 *
 */
public class Boj4597 {
	private static final String TERMINATE = "#";
	private static final String NEW_LINE = "\n";
	
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String bitString = br.readLine();
			if(bitString.equals(TERMINATE)) break;
			
			process(bitString.toCharArray());
		}
		
		System.out.print(sb.toString());
	}
	
	private static void process(char[] bit) {
		boolean flag = bit[bit.length - 1] == 'e' ? true: false;
		
		int count = 0;
		for(int i = 0; i < bit.length - 1; i++) {		// 갯수세기
			if(bit[i] == '1') count++;
			sb.append(bit[i]);
		}
		
		sb.append(flag ? (count % 2 == 0 ? 0: 1): (count % 2 == 0 ? 1: 0)).append(NEW_LINE);
	}
}

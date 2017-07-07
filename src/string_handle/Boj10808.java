package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj10808 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		
		String word = br.readLine();
		int len = word.length();
		
		char[] alpha = new char[len];
		alpha = word.toCharArray();
		
		int[] check = new int[26];
		
		for(int i = 0; i < len; i++){
			check[alpha[i] - 97]++;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < check.length; i++){
			sb.append(check[i]).append(SPACE);
		}
		
		System.out.println(sb.toString());
	}

}

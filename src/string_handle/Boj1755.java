package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1755번: 숫자 놀이
 *
 *	@see https://www.acmicpc.net/problem/1755/
 *
 */
public class Boj1755 {
	private static final String[] nums = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	private static class Sort implements Comparable<Sort>{
		int value;
		String str;
		
		public Sort(int value, String str) {
			this.value = value;
			this.str = str;
		}

		@Override
		public int compareTo(Sort s) {
			return this.str.compareTo(s.str);
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());		
		
		System.out.println(process(M, N));
	}
	
	private static StringBuilder process(int start, int end) {
		StringBuilder sb = new StringBuilder();
		Sort[] s = new Sort[end - start + 1];
		
		for(int i = start; i < end + 1; i++) {
			String numToString = String.valueOf(i);
			String tmp = "";
			int leng = numToString.length();
			
			for(int j = 0; j < leng; j++) {
				tmp += nums[numToString.charAt(j) - '0'];
			}
			
			s[i - start] = new Sort(i, tmp);
		}
		
		Arrays.sort(s);
		for(int i = 1; i < s.length + 1; i++) {		// 사전순 정렬 후 해당 데이터를 버퍼에 저장
			sb.append(s[i - 1].value);
			
			if(i % 10 == 0) sb.append(NEW_LINE);
			else sb.append(SPACE);
		}
		
		return sb;
	}
}

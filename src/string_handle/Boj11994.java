package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 11994번: NN
 *
 *	@see https://www.acmicpc.net/problem/11994/
 *
 */
public class Boj11994 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String num = st.nextToken();
		char[] numArr = num.toCharArray();
		int N = Integer.parseInt(num);
		int M = Integer.parseInt(st.nextToken());
		
		System.out.println(getN(numArr, N, M));		// 결과 출력
	}
	
	private static StringBuilder getN(char[] word, int num, int limit) {
		StringBuilder sb = new StringBuilder();
		
		int totalLeng = word.length * num;
		limit = totalLeng > limit ? limit : totalLeng;		// 길이 제한
		
		for(int i = 0; i < limit; i++) {
			sb.append(word[i % word.length]);
		}
		
		return sb;
	}
}

package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 6183번: word power
 *
 *	@see https://www.acmicpc.net/problem/6183/
 *
 */
public class Boj6183 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] cows = new char[N][];
		for(int i = 0; i < N; i++) {
			cows[i] = br.readLine().toLowerCase().toCharArray();
		}
		
		char[][] key = new char[M][];
		for(int i = 0; i < M; i++) {
			key[i] = br.readLine().toLowerCase().toCharArray();
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sb.append(comparing(cows[i], key)).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static int comparing(char[] target, char[][] src) {
		int count = 0;
		
		for(int i = 0; i < src.length; i++) {
			int idx = 0;
			
			for(int j = 0; j < target.length; j++) {
				if(idx == src[i].length) break;
				if(target[j] == src[i][idx]) idx++;		// 일치하는 단어가 순서대로 나올 때마다 ++
			}
			
			if(idx == src[i].length) count++;
		}
		
		return count;
	}
}

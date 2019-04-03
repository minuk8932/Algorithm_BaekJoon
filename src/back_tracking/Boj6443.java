package back_tracking;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 
 * 	@author minchoba
 *	백준 6443번: 애너그램
 *
 *	@see https://www.acmicpc.net/problem/6443/
 *
 */
public class Boj6443 {
	private static int[] alpha;
	private static char[] result;
	
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			alpha = new int[52];
			
			char[] input = br.readLine().toCharArray();
			
			for(char c: input) {								// 대소문자 구분 입력
				if(c >= 'A' && c <= 'Z') alpha[c - 'A']++;
				else alpha[c - 'a' + 26]++;
			}

			result = new char[input.length];
			backTracking(0, input.length);
		}
	}
	
	private static void backTracking(int depth, int limit) throws IOException {
		if(depth == limit) {
			for(int i = 0; i < result.length; i++) {		// 기저사례 답 바로 출력
				bw.write(result[i]);
			}
			bw.write(NEW_LINE);
			bw.flush();
			
			return;
		}
		
		for(int next = 0; next < alpha.length; next++) {
			if(alpha[next] == 0) continue;
			
			alpha[next]--;
			result[depth] = (char) (next < 26 ? next + 'A': next + 'a' - 26);		// 대문자 소문자 구분해서 배열에 담아줌

			backTracking(depth + 1, limit);
			alpha[next]++;
		}
	}
}

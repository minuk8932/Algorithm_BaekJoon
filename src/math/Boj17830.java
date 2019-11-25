package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17830번: 이진수씨의 하루 일과
 *
 *	@see https://www.acmicpc.net/problem/17830/
 *
 */
public class Boj17830 {
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	private static final char QU = '?';
	private static final char MAX = '1';
	private static final char MIN = '0';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			char[][] input = new char[2][N];
			String line = st.nextToken();
			for(int i = 0; i < N; i++) {
				input[1][i] = input[0][i] = line.charAt(i);
			}
			
			sb.append(todayBinary(N, input[0], MAX)).append(SPACE).append(todayBinary(N, input[1], MIN)).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static int todayBinary(int n, char[] arr, char instead) {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == QU) arr[i] = instead;			// fill '1' or '0'
		}
		
		int len = 0;
		int flag = 0;
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == MAX) flag++;					// first '1'
			if(flag > 0) len++;							// count cipher
		}
		
		len += n;
		
		if(flag == 0) len = 1;
		else if(flag == 1) len -= 1;
		
		return len;
	}
}

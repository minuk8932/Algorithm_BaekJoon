package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 5525번: IOIOI
 *
 *	@see https://www.acmicpc.net/problem/5525/
 *
 */
public class Boj5525 {
	private static final char[] IO = {'I', 'O'};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		char[] S = br.readLine().toCharArray();
		System.out.println(getCount(N, S));
	}

	private static int getCount(int n, char[] target) {
		int count = 0;
		
		for(int idx = 0; idx < target.length;) {
			if(target[idx] != IO[0]) {										// 첫자리가 I 인가
				idx++;
				continue;
			}
			
			int jdx = 0;
			while(idx < target.length && target[idx] == IO[jdx % 2]) {		// IOI의 구성 갯수
				idx++;
				jdx++;
			}
			
			if(jdx > 2 * n) {												// IOI가 원하는 크기 이상으로 나온 경우
				int seg = n * 2 + 1;
				count++;
				
				if(jdx > seg) count += (jdx - seg) / 2;						// IOI가 여러개일 경우 갯수 체크 
				idx -= 1;
			}
		}
		
		return count;
	}
}

package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15876번: Binary Counting
 *
 *	@see https://www.acmicpc.net/problem/15876/
 *
 */
public class Boj15876 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		String binary = setBinary(n);
		System.out.println(getResult(binary.toCharArray(), n, k - 1));
	}
	
	private static String setBinary(int N) {
		StringBuilder tmp = new StringBuilder();
		
		int leng = N * 5;
		int num = 2, idx = 1;
		
		while(num < leng) {
			num += (idx * Math.pow(2, idx));
			idx++;
		}
		
		int size = (int) (Math.pow(2, idx) - 1);
		
		for(int i = 0; i < size + 1; i++) {				// 이진수를 한줄로 이어 붙이기
			tmp.append(Integer.toBinaryString(i));
		}
		
		return tmp.toString();
	}
	
	private static StringBuilder getResult(char[] b, int seq, int index) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < 5; i++) {
			sb.append(b[i * seq + index]).append(SPACE);		// 자리에 맞는 값
		}
		
		return sb;
	}
}

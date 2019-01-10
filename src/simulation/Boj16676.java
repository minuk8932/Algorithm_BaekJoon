package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 16676번: 근우의 다이어리 꾸미기
 *
 *	@see https://www.acmicpc.net/problem/16676/
 *
 */
public class Boj16676 {
	private static final char[] NUMBER = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int res = 0;
		
		for(char start: NUMBER) {
			int length = getMaxString(N, start).length() - 1;		// 처음 입력한 0을 제거한 길이
			if(length > res) res = length;
		}
		
		System.out.println(res == 0 ? 1 : res);		// 0번이 필요하면 셋트 1개가 필요
	}
	
	private static String getMaxString(int n, char current) {
		String stream = "0";
		
		while(Long.parseLong(stream) <= n) {		// 주어진 수보다 커질때 까지 1개의 숫자로 가득 채움,
			stream += current;
		}
		
		return stream.substring(0, stream.length() - 1);		// 주어진 수보다 크면 가장 뒷자리 하나를 제거하고 반환
	}
}

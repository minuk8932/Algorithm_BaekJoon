package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 15725번: 다항함수의 미분
 *
 *	@see https://www.acmicpc.net/problem/15725/
 *
 */
public class Boj15725 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String formula = br.readLine();
		
		System.out.println(differential(formula.toCharArray()));
	}
	
	private static String differential(char[] form) {
		StringBuilder sb = new StringBuilder();
		boolean isOne = false;
		
		for(char c: form) {
			if(c != 'x') sb.append(c);
			else {						// 1차식인 경우
				isOne = true;
				break;
			}
		}
		
		String str = sb.toString();
		
		if(sb.length() == 0 || str.equals("-")) sb.append(1);		// 1차식의 계수가 1 또는 -1 인 경우
		if(sb.toString().equals("0")) {								// 상수항만 존재하는 경우1
			sb = new StringBuilder();
			sb.append(0);
		}
		
		return !isOne ? "0": sb.toString();					// 상수항만 존재하는 경우2
	}
}

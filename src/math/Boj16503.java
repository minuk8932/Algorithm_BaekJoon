package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16503번: 괄호 없는 사칙연산
 *
 *	@see https://www.acmicpc.net/problem/16503/
 *
 */
public class Boj16503 {
	private static final String NEW_LINE = "\n";
	private static final char MUL = '*';
	private static final char DIV = '/';
	private static final char ADD = '+';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int k1 = Integer.parseInt(st.nextToken());
		char o1 = st.nextToken().charAt(0);
		int k2 = Integer.parseInt(st.nextToken());
		char o2 = st.nextToken().charAt(0);
		int k3 = Integer.parseInt(st.nextToken());
		
		System.out.println(getMinMax(k1, o1, k2, o2, k3));
	}
	
	private static StringBuilder getMinMax(int K1, char O1, int K2, char O2, int K3) {
		StringBuilder sb = new StringBuilder();
		int sum1 = calculator(calculator(K1, O1, K2), O2, K3);
		int sum2 = calculator(K1, O1, calculator(K2, O2, K3));
		
		if(sum1 > sum2) sb.append(sum2).append(NEW_LINE).append(sum1);
		else sb.append(sum1).append(NEW_LINE).append(sum2);	
				
		return sb;
	}
	
	private static int calculator(int num1, char oper, int num2) {
		if(oper == MUL) return num1 * num2;
		else if(oper == DIV) return num1 / num2;
		else if(oper == ADD) return num1 + num2;
		else return num1 - num2;
	}
}

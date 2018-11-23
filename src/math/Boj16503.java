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
	private static final String MUL = "*";
	private static final String DIV = "/";
	private static final String ADD = "+";
	private static final String SUB = "-";
	
	private static boolean[][] isUsed = new boolean[2][4];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		System.out.println(getMinMax(Integer.parseInt(st.nextToken()), st.nextToken(), 
				Integer.parseInt(st.nextToken()), st.nextToken(), Integer.parseInt(st.nextToken())));
	}
	
	private static StringBuilder getMinMax(int K1, String O1, int K2, String O2, int K3) {
		StringBuilder sb = new StringBuilder();
		int sum1 = calculator(O1, K1, K2, 0);
		int sum2 = calculator(O2, K2, K3, 1);
		
		sum1 = nextCalculate(sum1, K3, 1);
		sum2 = nextCalculate(K1, sum2, 0);
		
		sb.append(sum1 < sum2 ? sum1 : sum2).append(NEW_LINE).append(sum1 > sum2 ? sum1 : sum2);		
		return sb;
	}
	
	private static int calculator(String oper, int num1, int num2, int idx) {
		int value = 0;
		
		switch(oper) {
		case MUL:
			isUsed[idx][0] = true;
			value = num1 * num2;
			break;
			
		case DIV:
			isUsed[idx][1] = true;
			value = num1 / num2;
			break;
			
		case ADD:
			isUsed[idx][2] = true;
			value = num1 + num2;
			break;
			
		case SUB:
			isUsed[idx][3] = true;
			value = num1 - num2;
			break;
		}
		
		return value;
	}

	private static int nextCalculate(int a, int b, int idx) {
		int val = 0;
		
		for(int i = 0; i < isUsed[idx].length; i++) {
			if(isUsed[idx][i]) {
				val = getValue(a, b, i);
			}
		}
		
		return val;
	}
	
	private static int getValue(int a, int b, int op) {
		if(op == 0) return a * b;
		else if(op == 1) return a / b;
		else if(op == 2) return a + b;
		else return a - b;
	}
}

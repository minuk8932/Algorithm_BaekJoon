package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 
 * 	@author minchoba
 *	백준 1935번: 후위표기식 2
 *
 *	@see https://www.acmicpc.net/problem/1935/
 *
 */
public class Boj1935 {
	private static final char PLUS = '+';
	private static final char MINUS = '-';
	private static final char MULTI = '*';
	private static final char DIVIDE = '/';
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char[] formula = br.readLine().toCharArray();
		Stack<Double> res = new Stack<>();
		
		double tmp = 0;
		double[] seq = new double[N];
		
		for(int i = 0; i < N; i++) {
			seq[i] = Double.parseDouble(br.readLine());		// 알파벳에 해당하는 값을 담아줌
		}
		
		for(int i = 0; i < formula.length; i++) {
			if(formula[i] >= 'A' && formula[i] <= 'Z') {	// 해당 식에서 알파벳이 나온 경우
				tmp = seq[formula[i] - 'A'];			// 스택에 해당 값을 double형으로 푸쉬
				res.push(tmp);
			}
			else {						// 연산이 나온 경우
				double a = res.pop();
				double b = res.pop();
				tmp = 0;
				
				switch(formula[i]) {	// 연산 종류에 맞춰 계산 후 다시 스택에 푸쉬
				case PLUS:
					tmp = b + a;
					res.push(tmp);
					break;
					
				case MINUS:
					tmp = b - a;
					res.push(tmp);
					break;
					
				case MULTI:
					tmp = b * a;
					res.push(tmp);
					break;
					
				case DIVIDE:
					tmp = b / a;
					res.push(tmp);
					break;
				}
			}
		}
		
		System.out.printf("%.2f", res.pop());		// 소수점 2자리에서 반올림한 결과 출력
	}
}

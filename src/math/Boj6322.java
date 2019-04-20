package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6322번: 직각삼각형의 두변
 *
 *	@see https://www.acmicpc.net/problem/6322/
 *
 */
public class Boj6322 {
	private static final String T = "Triangle #";
	private static final String I = "Impossible.";
	private static final String NEW_LINE = "\n";
	private static final String[] LINE = {"a = ", "b = ", "c = "};
	
	private static boolean flag;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = 1;
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a == 0 && b == 0 && c == 0) break;
			flag = true;
			
			sb.append(T).append(t++).append(NEW_LINE);
			double value = getLine(a, b, c);
			
			if(!flag) sb.append(I);
			else sb.append(a == -1 ? LINE[0] : b == -1 ? LINE[1]: LINE[2]).append(String.format("%.3f", value));
			
			sb.append(NEW_LINE).append(NEW_LINE);
		}
		
		System.out.print(sb);
	}
	
	private static double getLine(double x, double y, double z) {
		double result = 0;
		
		double xPow = Math.pow(x, 2);
		double yPow = Math.pow(y, 2);
		double zPow = Math.pow(z, 2);
		
		if(x == -1) {
			if(zPow <= yPow) flag = false;
			result = Math.sqrt(zPow - yPow);
		}
		else if(y == -1) {
			if(zPow <= xPow) flag = false;
			result = Math.sqrt(zPow - xPow);
		}
		else {
			result = Math.sqrt(xPow + yPow);
		}
		
		return result;
	}
}
